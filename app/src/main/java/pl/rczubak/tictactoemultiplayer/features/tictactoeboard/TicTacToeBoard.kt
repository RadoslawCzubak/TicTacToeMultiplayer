package pl.rczubak.tictactoemultiplayer.features.tictactoeboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TicTacToeBoardScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text("Game board")
    }
}

@Preview
@Composable
fun TicTacToeBoardPreview() {
    TicTacToeBoardScreen()
}