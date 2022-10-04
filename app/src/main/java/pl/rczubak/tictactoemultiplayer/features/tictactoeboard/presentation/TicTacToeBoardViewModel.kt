package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.GameState
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.usecase.MakeMoveUseCase
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model.GameBoardDisplayable

class TicTacToeBoardViewModel(
    private val makeMoveUseCase: MakeMoveUseCase
) : ViewModel() {
    private val _boardState = MutableStateFlow(GameBoardDisplayable.empty())
    val boardState: StateFlow<GameBoardDisplayable> = _boardState

    private val _winPath = MutableStateFlow<List<Pair<Int, Int>>?>(null)
    val winPath: StateFlow<List<Pair<Int, Int>>?> = _winPath

    fun makeMove(x: Int, y: Int) {
        when (val nextGameState = makeMoveUseCase(x, y)) {
            is GameState.End -> {
                _winPath.value = nextGameState.winPath
                _boardState.value = GameBoardDisplayable(nextGameState.gameBoard)
            }
            is GameState.InProgress -> _boardState.value =
                GameBoardDisplayable(nextGameState.gameBoard)
        }
    }
}