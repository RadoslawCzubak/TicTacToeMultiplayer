package pl.rczubak.tictactoemultiplayer.core.data.model

import android.bluetooth.BluetoothDevice
import pl.rczubak.tictactoemultiplayer.core.utils.currentTimeInSecs

data class BluetoothDeviceRemote(
    val deviceName: String,
    val address: String,
    val foundTimestamp: Long,
    val bluetoothDevice: BluetoothDevice
) {
    constructor(bluetoothDevice: BluetoothDevice) : this(
        bluetoothDevice.name,
        bluetoothDevice.address,
        currentTimeInSecs(),
        bluetoothDevice
    )
}
