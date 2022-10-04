package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain

import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.GameBoard
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.GameState
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.Player

interface GameRepository {
    fun startGame(): GameBoard
    fun getCurrentGameState(): GameBoard
    fun addMove(x: Int, y: Int): GameBoard
}