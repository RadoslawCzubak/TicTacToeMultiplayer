package pl.rczubak.tictactoemultiplayer.core.navigation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.ActivityIntegrationPoint
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import kotlinx.parcelize.Parcelize
import pl.rczubak.tictactoemultiplayer.features.clientplayerlobby.presentation.ClientLobbyScreen
import pl.rczubak.tictactoemultiplayer.features.hostplayerlobby.presentation.HostLobbyScreen
import pl.rczubak.tictactoemultiplayer.features.mainmenu.navigation.MainMenuNavigatorImpl
import pl.rczubak.tictactoemultiplayer.features.mainmenu.presentation.MainMenuScreen

@Composable
fun AppNodeHost(integrationPoint: ActivityIntegrationPoint) {
    NodeHost(integrationPoint = integrationPoint) {
        RootNode(it)
    }
}

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = NavTarget.MainMenu,
        savedStateMap = buildContext.savedStateMap
    )
) :
    ParentNode<RootNode.NavTarget>(
        navModel = backStack,
        buildContext = buildContext
    ) {

    private val navigator = object : ScreenNavigator {
        override fun navigate(destination: NavigationScreen) {
            when (destination) {
                NavigationScreen.ClientLobby -> backStack.push(NavTarget.ClientLobby)
                NavigationScreen.HostLobby -> backStack.push(NavTarget.HostLobby)
                NavigationScreen.MainMenu -> backStack.push(NavTarget.MainMenu)
                NavigationScreen.TicTacToeGame -> Unit
            }
        }
    }

    sealed class NavTarget : Parcelable {
        @Parcelize
        object MainMenu : NavTarget()

        @Parcelize
        object ClientLobby : NavTarget()

        @Parcelize
        object HostLobby : NavTarget()
    }

    @Composable
    override fun View(modifier: Modifier) {
        Children(navModel = backStack)
    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node {
        return when (navTarget) {
            NavTarget.MainMenu -> MainMenuNode(buildContext, navigator)
            NavTarget.ClientLobby -> ClientLobbyNode(buildContext)
            NavTarget.HostLobby -> HostLobbyNode(buildContext)
        }
    }
}


class MainMenuNode(
    buildContext: BuildContext,
    private val navigator: ScreenNavigator
) : Node(buildContext) {
    @Composable
    override fun View(modifier: Modifier) {
        MainMenuScreen(navigator = MainMenuNavigatorImpl(navigator = navigator))
    }
}


class ClientLobbyNode(
    buildContext: BuildContext
) : Node(buildContext) {
    @Composable
    override fun View(modifier: Modifier) {
        ClientLobbyScreen()
    }
}


class HostLobbyNode(
    buildContext: BuildContext
) : Node(buildContext) {
    @Composable
    override fun View(modifier: Modifier) {
        HostLobbyScreen()
    }
}