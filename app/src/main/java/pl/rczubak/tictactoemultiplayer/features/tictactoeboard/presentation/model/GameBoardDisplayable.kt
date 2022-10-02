package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.model

data class GameBoardDisplayable(
    val board: List<List<SquareState>>
) {
    companion object {
        fun empty(): GameBoardDisplayable {
            return GameBoardDisplayable(List(3) { List(3) { SquareState.EMPTY } })
        }
    }
}

enum class SquareState {
    X, O, EMPTY
}
