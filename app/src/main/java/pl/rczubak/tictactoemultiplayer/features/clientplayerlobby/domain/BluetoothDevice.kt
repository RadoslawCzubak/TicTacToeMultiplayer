package pl.rczubak.tictactoemultiplayer.features.clientplayerlobby.domain

data class BluetoothDevice(
    val deviceName: String,
    val address: String,
    val foundTimestamp: Long
)
