package pl.rczubak.tictactoemultiplayer.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pl.rczubak.tictactoemultiplayer.core.theme.TicTacToeMultiplayerTheme
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.TicTacToeBoardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeMultiplayerTheme {
                // A surface container using the 'background' color from the theme
                TicTacToeBoardScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeMultiplayerTheme {
        Greeting("Android")
    }
}