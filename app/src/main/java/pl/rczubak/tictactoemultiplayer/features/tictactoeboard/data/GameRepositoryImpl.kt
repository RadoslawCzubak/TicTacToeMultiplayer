package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.data

import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.GameRepository
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.GameBoard

class GameRepositoryImpl : GameRepository {
    private var game: GameBoard = GameBoard.empty()

    override fun startGame(): GameBoard {
        game = GameBoard.empty()
        return game
    }

    override fun getCurrentGameState(): GameBoard {
        return game
    }

    override fun addMove(x: Int, y: Int): GameBoard {
        val newGameBoard = game.board.map {
            it.toMutableList()
        }.toMutableList()
        newGameBoard[x][y] = game.turn
        game = GameBoard(newGameBoard, turn = game.nextPlayer)
        return game
    }
}