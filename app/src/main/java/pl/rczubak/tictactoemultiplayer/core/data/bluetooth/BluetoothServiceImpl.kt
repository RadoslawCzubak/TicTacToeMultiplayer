package pl.rczubak.tictactoemultiplayer.core.data.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import androidx.work.impl.utils.ForceStopRunnable
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import pl.rczubak.tictactoemultiplayer.core.utils.bluetoothStateToDomain

class BluetoothServiceImpl(
    private val context: Context,
    private val bluetoothAdapter: BluetoothAdapter
) : BluetoothService {
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
}