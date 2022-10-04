package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model

import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.GameBoard
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model.Player

data class GameBoardDisplayable(
    val board: List<List<PlayerDisplayable?>>,
    val turn: PlayerDisplayable,
    val nextPlayer: PlayerDisplayable
) {
    constructor(gameBoard: GameBoard) : this(
        gameBoard.board.map {
            it.map {
                when (it) {
                    Player.O -> PlayerDisplayable.O
                    Player.X -> PlayerDisplayable.X
                    null -> null
                }
            }
        },
        PlayerDisplayable.fromDomain(gameBoard.turn),
        PlayerDisplayable.fromDomain(gameBoard.nextPlayer)
    )

    companion object {
        fun empty(): GameBoardDisplayable {
            return GameBoardDisplayable(
                List(3) { List(3) { null } },
                PlayerDisplayable.X,
                PlayerDisplayable.O
            )
        }
    }
}

enum class PlayerDisplayable {
    X, O;

    companion object {
        fun fromDomain(player: Player): PlayerDisplayable {
            return when (player) {
                Player.X -> X
                Player.O -> O
            }
        }
    }

    fun getOppositePlayer(): PlayerDisplayable {
        return when (this) {
            X -> O
            O -> X
        }
    }
}
