package pl.rczubak.tictactoemultiplayer.features.tictactoeboard.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.data.GameRepositoryImpl
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.GameRepository
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.domain.usecase.MakeMoveUseCase
import pl.rczubak.tictactoemultiplayer.features.tictactoeboard.presentation.TicTacToeBoardViewModel

val gameBoardModule = module {

    single<GameRepository> {
        GameRepositoryImpl()
    }

    single {
        MakeMoveUseCase(get())
    }

    viewModel {
        TicTacToeBoardViewModel(get())
    }
}