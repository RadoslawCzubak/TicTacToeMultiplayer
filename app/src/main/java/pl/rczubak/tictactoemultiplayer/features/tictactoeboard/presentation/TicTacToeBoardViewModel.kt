package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.usecase.MakeMoveUseCase
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.GameBoardDisplayable
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.SquareState

class TicTacToeBoardViewModel(
    private val makeMoveUseCase: MakeMoveUseCase
) : ViewModel() {
    private val _boardState = MutableStateFlow(GameBoardDisplayable.empty())
    val boardState: StateFlow<GameBoardDisplayable> = _boardState

    private val _player = MutableStateFlow(false)
    val player: StateFlow<Boolean> = _player

    fun makeMove(x: Int, y: Int) {
        val currentPlayerValue = _player.value
        if (_boardState.value.board[x][y] != SquareState.EMPTY) return
        val board = _boardState.value.board.map { it.toMutableList() }.toMutableList()
        board[x][y] = if (currentPlayerValue) SquareState.X else SquareState.O
        _player.value = !currentPlayerValue
        _boardState.value = GameBoardDisplayable(board)
    }
}