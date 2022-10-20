package pl.rczubak.tictactoemultiplayer.core.utils

import android.bluetooth.BluetoothAdapter
import pl.rczubak.tictactoemultiplayer.core.domain.model.BluetoothState

fun bluetoothStateToDomain(state: Int?): BluetoothState {
    return when (state) {
        BluetoothAdapter.STATE_ON -> BluetoothState.ON
        BluetoothAdapter.STATE_TURNING_ON -> BluetoothState.TURNING_ON
        BluetoothAdapter.STATE_TURNING_OFF -> BluetoothState.TURNING_OFF
        BluetoothAdapter.STATE_OFF -> BluetoothState.OFF
        else -> BluetoothState.NOT_AVAILABLE
    }
}