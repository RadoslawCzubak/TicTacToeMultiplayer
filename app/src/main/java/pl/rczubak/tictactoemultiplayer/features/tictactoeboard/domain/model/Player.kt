package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model

enum class Player {
    X, O;

    fun getOppositePlayer(): Player{
        return if (this == X) O else X
    }
}