package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.GameBoardDisplayable
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.SquareState

@Composable
fun TicTacToeBoardScreen(
    viewModel: TicTacToeBoardViewModel = koinViewModel()
) {
    val boardState by viewModel.boardState.collectAsState()
    val turnInfo by viewModel.player.collectAsState()

    GameView(
        turnInfo = turnInfo,
        boardState = boardState,
        onSquareClick = {
            viewModel.makeMove(it.first, it.second)
        }
    )
}

@Composable
fun GameView(
    turnInfo: Boolean,
    boardState: GameBoardDisplayable,
    onSquareClick: (Pair<Int, Int>) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {
        TurnInfo(isPlayer1 = turnInfo)
        GameBoardView(
            boardState = boardState,
            onSquareClick = onSquareClick
        )
    }
}

@Composable
fun TurnInfo(isPlayer1: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Player turn: ")
        Square(squareState = if (isPlayer1) SquareState.X else SquareState.O, heightWidth = 30.dp)
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
    squareState: SquareState,
    heightWidth: Dp = 90.dp,
    onClick: () -> Unit = {}
) {
    val backgroundColorModifier = when (squareState) {
        SquareState.X -> Modifier.background(Color.Blue)
        SquareState.O -> Modifier.background(Color.Red)
        SquareState.EMPTY -> Modifier.background(Color.LightGray)
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
    GameView(true, GameBoardDisplayable.empty(), {})
}