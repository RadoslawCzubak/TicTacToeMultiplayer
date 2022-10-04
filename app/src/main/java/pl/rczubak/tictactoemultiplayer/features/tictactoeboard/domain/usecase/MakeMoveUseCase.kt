package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.usecase

import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.GameRepository
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.GameBoard
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.GameState

class MakeMoveUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(
        x: Int,
        y: Int
    ): GameState {
        val newGameBoard = repository.addMove(x, y)
        return when(val winPath = newGameBoard.getWinPathIfGameEndElseNull()){
            null -> GameState.InProgress(newGameBoard)
            else -> GameState.End(newGameBoard, winner = newGameBoard.nextPlayer, winPath = winPath )
        }
    }
}