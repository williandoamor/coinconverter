package br.com.wda.bc_integration.di

import android.app.Application
import br.com.wda.bc_integration.di.mainModule.MainModules
import br.com.wda.bc_integration.di.DataModule.DataModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
        }

        DataModules.load()
        MainModules.load()
    }
}