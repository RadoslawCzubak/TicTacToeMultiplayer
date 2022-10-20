package pl.rczubak.tictactoemultiplayer.core.data.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver
import pl.rczubak.tictactoemultiplayer.core.domain.model.BluetoothState
import pl.rczubak.tictactoemultiplayer.core.utils.bluetoothStateToDomain

class BluetoothBroadcastReceiver(
    private val onStateChanged: (BluetoothState) -> Unit
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        val newState = intent?.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
        Log.d("BluetoothBroadcastReceiver", "$newState")
        onStateChanged(bluetoothStateToDomain(newState))
    }
}