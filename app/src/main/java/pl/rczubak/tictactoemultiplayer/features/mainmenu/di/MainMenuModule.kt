package pl.rczubak.tictactoemultiplayer.features.mainmenu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.rczubak.tictactoemultiplayer.features.mainmenu.navigation.MainMenuNavigator
import pl.rczubak.tictactoemultiplayer.features.mainmenu.navigation.MainMenuNavigatorImpl
import pl.rczubak.tictactoemultiplayer.features.mainmenu.presentation.MainMenuViewModel

val mainModuleModule = module {

    single<MainMenuNavigator> { MainMenuNavigatorImpl(get()) }

    viewModel { MainMenuViewModel() }
}