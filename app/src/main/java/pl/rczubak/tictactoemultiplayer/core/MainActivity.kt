package pl.rczubak.tictactoemultiplayer.core

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.integrationpoint.NodeComponentActivity
import pl.rczubak.tictactoemultiplayer.core.navigation.AppNodeHost
import pl.rczubak.tictactoemultiplayer.core.theme.TicTacToeMultiplayerTheme

class MainActivity : NodeComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeMultiplayerTheme {
                AppNodeHost(integrationPoint = integrationPoint)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeMultiplayerTheme {}
}