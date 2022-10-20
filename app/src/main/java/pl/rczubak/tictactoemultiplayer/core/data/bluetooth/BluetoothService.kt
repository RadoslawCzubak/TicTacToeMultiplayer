package pl.rczubak.tictactoemultiplayer.core.data.bluetooth

import kotlinx.coroutines.flow.Flow
import pl.rczubak.tictactoemultiplayer.core.domain.model.BluetoothState

interface BluetoothService {
    val bluetoothStatus: Flow<BluetoothState>
}

