package pl.rczubak.tictactoemultiplayer.core.di

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pl.rczubak.tictactoemultiplayer.core.data.bluetooth.BluetoothService
import pl.rczubak.tictactoemultiplayer.core.data.bluetooth.BluetoothServiceImpl

val appModule = module {
    single {
        val bluetoothManager: BluetoothManager =
            androidContext().getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
        bluetoothAdapter
    }

    single<BluetoothService> {
        BluetoothServiceImpl(androidContext(), get())
    }
}