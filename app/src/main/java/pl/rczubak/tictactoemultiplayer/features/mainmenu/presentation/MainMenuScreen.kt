package pl.rczubak.tictactoemultiplayer.features.mainmenu.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainMenuScreen(viewModel: MainMenuViewModel = koinViewModel()) {
    MainMenuContent(
        onClientClick = {
            viewModel.navigateToClient()
        },
        onHostClick = {
            viewModel.navigateToHost()
        }
    )
}

@Composable
fun MainMenuContent(
    onHostClick: () -> Unit = {},
    onClientClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameTitle()

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = onHostClick) {
                Text(text = "Host a game")
            }

            Button(onClick = onClientClick) {
                Text(text = "Join game")
            }
        }
    }
}

@Composable
fun GameTitle() {
    Text(text = "Tic Tac Toe", fontSize = 30.sp)
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MainMenuPreview() {
    MainMenuContent()
}