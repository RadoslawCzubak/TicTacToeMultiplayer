package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.usecase.MakeMoveUseCase
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.TicTacToeBoardViewModel

val gameBoardModule = module {
    single {
        MakeMoveUseCase()
    }

    viewModel {
        TicTacToeBoardViewModel(get())
    }
}