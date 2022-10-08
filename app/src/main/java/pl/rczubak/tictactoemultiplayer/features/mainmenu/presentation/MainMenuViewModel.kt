package pl.rczubak.tictactoemultiplayer.features.mainmenu.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainMenuViewModel : ViewModel() {
    private val _navigateToHost by lazy { MutableStateFlow(false) }
    val navigateToHost = _navigateToHost.asStateFlow()

    private val _navigateToClient by lazy { MutableStateFlow(false) }
    val navigateToClient = _navigateToClient.asStateFlow()

    fun navigateToClient() {
        _navigateToClient.value = true
    }

    fun navigateToHost() {
        _navigateToHost.value = true
    }
}