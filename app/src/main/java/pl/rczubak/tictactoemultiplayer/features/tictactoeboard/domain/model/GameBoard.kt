package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model

import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.utils.transpose

data class GameBoard(
    val board: List<List<Player?>>,
    val turn: Player
) {
    val nextPlayer
        get() = turn.getOppositePlayer()

    fun getWinPathIfGameEndElseNull(): List<Pair<Int, Int>>? {
        //TODO: win checking doesn't work
        for (row in board) {
            if (row.all { it == Player.X } || row.all { it == Player.O })
                return row.map { Pair(board.indexOf(row), row.indexOf(it)) }
        }
        for (col in board.transpose()) {
            if (col.all { it == Player.X } || col.all { it == Player.O })
                return col.map { Pair(col.indexOf(it), board.indexOf(col)) }
        }
        val diagonals = listOf(
            listOf(board[0][0], board[1][1], board[2][2]),
            listOf(board[2][0], board[1][1], board[0][2])
        )
        for (diagonal in diagonals) {
            if (diagonal.all { it == Player.X } || diagonal.all { it == Player.O })
                return diagonal.map { Pair(board.indexOf(diagonal), diagonal.indexOf(it)) }
        }
        return null
    }

    companion object {
        fun empty() = GameBoard(
            List(3) { List(3) { null } }, Player.O
        )
    }
}
