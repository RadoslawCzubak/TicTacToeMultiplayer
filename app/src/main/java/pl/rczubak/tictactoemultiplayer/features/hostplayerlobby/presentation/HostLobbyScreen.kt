package pl.rczubak.tictactoemultiplayer.features.hostplayerlobby.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.get
import pl.rczubak.tictactoemultiplayer.core.data.bluetooth.BluetoothService
import pl.rczubak.tictactoemultiplayer.core.domain.model.BluetoothState

@Composable
fun HostLobbyScreen(bluetoothService: BluetoothService = get()) {
    val btState by
        bluetoothService.bluetoothStatus.collectAsState(initial = BluetoothState.NOT_AVAILABLE)
    HostLobbyContent(btState)
}

@Composable
fun HostLobbyContent(btState: BluetoothState) {
    Text("Host Lobby $btState")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HostLobbyPreview() {
    HostLobbyContent(BluetoothState.NOT_AVAILABLE)
}