package es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details

import android.app.Application
import es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.di.appModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}