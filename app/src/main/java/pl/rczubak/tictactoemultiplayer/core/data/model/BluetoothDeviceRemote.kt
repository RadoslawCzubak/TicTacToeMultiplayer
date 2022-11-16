package pl.rczubak.tictactoemultiplayer.core.data.model

import android.bluetooth.BluetoothDevice

data class BluetoothDeviceRemote(
    val deviceName: String,
    val address: String,
    val foundTimestamp: Long,
    val bluetoothDevice: BluetoothDevice
) {
    constructor(bluetoothDevice: BluetoothDevice) : this(
        bluetoothDevice.name,
        bluetoothDevice.address,
        System.currentTimeMillis(),
        bluetoothDevice
    )
}
