package es.rafapuig.pmdm.clean.authentication

import android.app.Application
import es.rafapuig.pmdm.clean.authentication.di.authModule
import es.rafapuig.pmdm.clean.authentication.di.dataStoreModule
import es.rafapuig.pmdm.clean.authentication.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AuthApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AuthApp)
            modules(
                dataStoreModule,
                networkModule,
                authModule
            )
        }
    }
}
