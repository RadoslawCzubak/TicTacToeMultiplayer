package pl.rczubak.tictactoemultiplayer.features.hostplayerlobby.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HostLobbyScreen() {
    HostLobbyContent()
}

@Composable
fun HostLobbyContent() {
    Text("Host Lobby")
}

@Preview
@Composable
fun HostLobbyPreview() {
    HostLobbyContent()
}