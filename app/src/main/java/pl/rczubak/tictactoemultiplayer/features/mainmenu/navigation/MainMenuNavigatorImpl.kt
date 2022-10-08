package pl.rczubak.tictactoemultiplayer.features.mainmenu.navigation

import pl.rczubak.tictactoemultiplayer.core.navigation.NavigationScreen
import pl.rczubak.tictactoemultiplayer.core.navigation.ScreenNavigator

class MainMenuNavigatorImpl(
    private val navigator: ScreenNavigator
) : MainMenuNavigator {
    override fun navigateToHostLobby() {
        navigator.navigate(NavigationScreen.HostLobby)
    }

    override fun navigateToClientLobby() {
        navigator.navigate(NavigationScreen.ClientLobby)
    }
}