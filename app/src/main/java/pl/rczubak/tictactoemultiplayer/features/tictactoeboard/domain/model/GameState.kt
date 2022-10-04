package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.model

sealed class GameState(gameBoard: GameBoard) {
    class End(val gameBoard: GameBoard, val winner: Player, val winPath: List<Pair<Int, Int>>) :
        GameState(gameBoard)

    class InProgress(val gameBoard: GameBoard) : GameState(gameBoard)
}
