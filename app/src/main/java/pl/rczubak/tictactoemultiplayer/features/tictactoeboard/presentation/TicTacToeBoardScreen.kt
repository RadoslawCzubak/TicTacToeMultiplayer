package pl.rczubak.tictactoemultiplayer.features.tictactoeboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.TicTacToeBoardViewModel
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.GameBoardDisplayable
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.SquareState

@Composable
fun TicTacToeBoardScreen(
    viewModel: TicTacToeBoardViewModel = koinViewModel()
) {
    val boardState by viewModel.boardState.collectAsState()

    GameBoardView(
        boardState,
        onSquareClick = {
            viewModel.makeMove(it.first, it.second)
        }
    )
}

@Composable
fun GameBoardView(
    boardState: GameBoardDisplayable,
    onSquareClick: (Pair<Int, Int>) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
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
}

@Composable
fun Square(
    squareState: SquareState,
    onClick: () -> Unit
) {
    val backgroundColorModifier = when (squareState) {
        SquareState.X -> Modifier.background(Color.Blue)
        SquareState.O -> Modifier.background(Color.Red)
        SquareState.EMPTY -> Modifier.background(Color.LightGray)
    }

    Box(
        modifier = Modifier
            .height(90.dp)
            .width(90.dp)
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
    GameBoardView(GameBoardDisplayable.empty(), {})
}