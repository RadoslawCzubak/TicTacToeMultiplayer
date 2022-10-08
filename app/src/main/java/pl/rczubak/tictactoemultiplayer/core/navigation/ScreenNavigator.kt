package pl.rczubak.tictactoemultiplayer.core.navigation

interface ScreenNavigator {
    fun navigate(destination: NavigationScreen)
}

sealed interface NavigationScreen{
    object MainMenu: NavigationScreen
    object ClientLobby: NavigationScreen
    object HostLobby: NavigationScreen
    object TicTacToeGame: NavigationScreen
}