package pl.rczubak.tictactoemultiplayer.core.data.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import androidx.work.impl.utils.ForceStopRunnable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import pl.rczubak.tictactoemultiplayer.core.data.model.BluetoothDeviceRemote
import pl.rczubak.tictactoemultiplayer.core.utils.bluetoothStateToDomain
import timber.log.Timber
import java.io.IOException
import java.util.*

class BluetoothServiceImpl(
    private val context: Context,
    private val bluetoothAdapter: BluetoothAdapter
) : BluetoothService {

    companion object {
        val SERVER_UUID: UUID = UUID.fromString("609232fb-04a4-46f2-a9f6-11fafc851077")
        val CLIENT_UUID: UUID = UUID.fromString("e37e3a20-e29a-4e70-96f0-7d4df2e17e4a")
    }

    private var connectionSocket: BluetoothSocket? = null

    private val visibleDevices: MutableList<BluetoothDeviceRemote> = mutableListOf()

    override val bluetoothStatus = callbackFlow {
        trySend(bluetoothStateToDomain(bluetoothAdapter.state))
        val br: ForceStopRunnable.BroadcastReceiver = BluetoothBroadcastReceiver(onStateChanged = {
            trySend(it)
        })
        val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        ContextCompat.registerReceiver(context, br, intentFilter, Context.RECEIVER_NOT_EXPORTED)
        awaitClose {
            context.unregisterReceiver(br)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun discoverDevices(): Flow<List<BluetoothDeviceRemote>> {
        return callbackFlow {
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    when (intent.action) {
                        BluetoothDevice.ACTION_FOUND -> {
                            val device: BluetoothDevice =
                                intent.getParcelableExtra(
                                    BluetoothDevice.EXTRA_DEVICE,
                                    BluetoothDevice::class.java
                                )!!
                            trySend(BluetoothDeviceRemote(device))
                        }
                    }
                }
            }
            this.invokeOnClose {
                context.unregisterReceiver(receiver)
                visibleDevices.clear()
            }
        }.scan(emptyList()) { acc, value ->
            val accumulated = acc + value
            visibleDevices.let {
                it.clear()
                it.addAll(accumulated)
            }
            accumulated
        }
    }

    suspend fun openServer() {
        return suspendCancellableCoroutine { continuation ->
            val serverSocket: BluetoothServerSocket? by lazy(LazyThreadSafetyMode.NONE) {
                bluetoothAdapter.listenUsingRfcommWithServiceRecord(
                    "TicTacToeGame",
                    SERVER_UUID
                )
            }
            var waitingForClient = true
            while (waitingForClient) {
                connectionSocket = try {
                    serverSocket?.accept()
                } catch (e: IOException) {
                    Timber.e("Server socket connection accept failed!", e)
                    waitingForClient = false
                    null
                }
                connectionSocket?.also {
                    serverSocket?.close()
                    waitingForClient = false
                }
            }
            continuation.invokeOnCancellation {
                try {
                    serverSocket?.close()
                } catch (e: IOException) {
                    Timber.e("Cannot close serverSocket", e)
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun connectToDevice(address: String) {
        return suspendCancellableCoroutine { continuation ->
            val device = visibleDevices.first { it.address == address }.bluetoothDevice
            connectionSocket =
                device.createRfcommSocketToServiceRecord(CLIENT_UUID)

            bluetoothAdapter.cancelDiscovery()
            connectionSocket?.let { socket ->
                socket.connect()
                continuation.resume(Unit, onCancellation = null)
            }

            continuation.invokeOnCancellation {
                try {
                    connectionSocket?.close()
                } catch (e: IOException) {
                    Timber.e("Cannot close socket!", e)
                }
            }
        }
    }

    fun observeForMessages(): Flow<BluetoothTransfer> {
        return callbackFlow {
            val inStream = connectionSocket?.inputStream
            val buffer = ByteArray(1024)

            var numBytes: Int
            while (true) {
                numBytes = try {
                    inStream?.read(buffer) ?: throw IOException()

                } catch (e: IOException) {
                    Timber.e("Input stream was disconnected", e)
                    trySend(BluetoothTransfer.ConnectionClosed)
                    break
                }
                trySend(BluetoothTransfer.ReceivedMessage(numBytes, buffer))
            }
        }
            .flowOn(Dispatchers.IO)
    }

    suspend fun sendMessage(bytes: ByteArray): BluetoothTransfer {
        val outStream = connectionSocket?.outputStream
        try {
            withContext(Dispatchers.IO) {
                outStream?.write(bytes)
            }
        } catch (e: IOException) {
            Timber.e("Cannot send a message", e)
            return BluetoothTransfer.SendingError(e.message ?: "Message was not sent.")
        }
        return BluetoothTransfer.SendingSuccess
    }
}

sealed interface BluetoothTransfer {
    data class ReceivedMessage(
        val numBytes: Int,
        val byteArray: ByteArray
    ) : BluetoothTransfer

    object SendingSuccess : BluetoothTransfer

    data class SendingError(val errorInfo: String) : BluetoothTransfer

    object ConnectionClosed : BluetoothTransfer
}