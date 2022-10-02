package pl.rczubak.tictactoemultiplayer.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(koinInjector)
        }
    }
}