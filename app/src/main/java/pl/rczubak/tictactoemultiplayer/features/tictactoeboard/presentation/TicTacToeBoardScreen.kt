package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.GameBoardDisplayable
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.PlayerDisplayable

@Composable
fun TicTacToeBoardScreen(
    viewModel: TicTacToeBoardViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val boardState by viewModel.boardState.collectAsState()
    val winPath by viewModel.winPath.collectAsState()
    val nextPlayer by viewModel.nextPlayer.collectAsState()

    LaunchedEffect(key1 = winPath) {
        if (winPath != null)
            Toast.makeText(
                context,
                "${winPath!![0].first}${winPath!![0].second} ${winPath!![1].first}${winPath!![1].second} ${winPath!![2].first}${winPath!![2].second}",
                Toast.LENGTH_LONG
            ).show()
    }

    GameView(
        boardState = boardState,
        onSquareClick = {
            viewModel.makeMove(it.first, it.second)
        }
    )
}

@Composable
fun GameView(
    boardState: GameBoardDisplayable,
    onSquareClick: (Pair<Int, Int>) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {
        TurnInfo(boardState.turn)
        GameBoardView(
            boardState = boardState,
            onSquareClick = onSquareClick
        )
    }
}

@Composable
fun TurnInfo(player: PlayerDisplayable) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Player turn: ")
        Square(squareState = player, heightWidth = 30.dp)
    }
}

@Composable
fun GameBoardView(
    boardState: GameBoardDisplayable,
    onSquareClick: (Pair<Int, Int>) -> Unit
) {
    Column {
        boardState.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, sqValue ->
                    Square(sqValue, onClick = {
                        onSquareClick(Pair(rowIndex, colIndex))
                    })
                }
            }
        }
    }
}

@Composable
fun Square(
    squareState: PlayerDisplayable?,
    heightWidth: Dp = 90.dp,
    onClick: () -> Unit = {}
) {
    val backgroundColorModifier = when (squareState) {
        PlayerDisplayable.X -> Modifier.background(Color.Blue)
        PlayerDisplayable.O -> Modifier.background(Color.Red)
        null -> Modifier.background(Color.LightGray)
    }

    Box(
        modifier = Modifier
            .height(heightWidth)
            .width(heightWidth)
            .padding(4.dp)
            .then(backgroundColorModifier)
            .clickable {
                onClick()
            }
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TicTacToeBoardPreview() {
    GameView(GameBoardDisplayable.empty(), {})
}