package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.usecase

import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.GameRepository
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.GameState

class MakeMoveUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(
        x: Int,
        y: Int
    ): GameState {
        val currentGameBoard = repository.getCurrentGameState()
        return if (repository.getCurrentGameState().board[x][y] == null) {
            val newGameBoard = repository.addMove(x, y)
            when (val winPath = newGameBoard.getWinPathIfGameEndElseNull()) {
                null -> GameState.InProgress(newGameBoard)
                else -> GameState.End(
                    newGameBoard,
                    winner = newGameBoard.nextPlayer,
                    winPath = winPath
                )
            }
        } else GameState.InProgress(currentGameBoard)
    }
}