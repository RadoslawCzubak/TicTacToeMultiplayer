package pl.rczubak.tictactoemultiplayer.core.di

import org.koin.core.module.Module
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.di.gameBoardModule

val koinInjector: List<Module> = appModule
    .plus(gameBoardModule)