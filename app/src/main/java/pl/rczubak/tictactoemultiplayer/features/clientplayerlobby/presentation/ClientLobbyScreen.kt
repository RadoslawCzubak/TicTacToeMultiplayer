package pl.rczubak.tictactoemultiplayer.features.clientplayerlobby.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ClientLobbyScreen() {
    ClientLobbyContent()
}

@Composable
fun ClientLobbyContent() {
    Text(text = "Client Lobby")
}

@Preview
@Composable
fun ClientLobbyPreview() {
    ClientLobbyContent()
}