package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model

import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.utils.transpose

data class GameBoard(
    val board: List<List<Player?>>,
    val turn: Player
) {
    val nextPlayer
        get() = turn.getOppositePlayer()

    fun getWinPathIfGameEndElseNull(): List<Pair<Int, Int>>? {
        board.forEachIndexed { rowIndex, row ->
            if (row.all { it == Player.X } || row.all { it == Player.O })
                return List(row.size) { index -> Pair(rowIndex, index) }
        }
        board.transpose().forEachIndexed { colIndex, col ->
            if (col.all { it == Player.X } || col.all { it == Player.O })
                return List(col.size) { index -> Pair(index, colIndex) }
        }
        val diagonals = listOf(
            listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
            listOf(Pair(2, 0), Pair(1, 1), Pair(0, 2))
        )
        for (diagonal in diagonals) {
            val diagonalItems = diagonal.map { board[it.first][it.second] }
            if (diagonalItems
                    .all { it == Player.X } || diagonalItems.all { it == Player.O }
            )
                return diagonal
        }
        return null
    }

    companion object {
        fun empty() = GameBoard(
            List(3) { List(3) { null } }, Player.O
        )
    }
}
