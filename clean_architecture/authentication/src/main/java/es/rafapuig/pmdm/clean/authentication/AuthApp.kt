package es.rafapuig.pmdm.clean.authentication

import android.app.Application
import es.rafapuig.pmdm.clean.authentication.di.authModule
import es.rafapuig.pmdm.clean.authentication.di.dataStoreModule
import es.rafapuig.pmdm.clean.authentication.di.fakeAuthModule
import es.rafapuig.pmdm.clean.authentication.di.fakeNetworkModule
import es.rafapuig.pmdm.clean.authentication.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * No olvides registrar la Application en el AndroidManifest.xml 👇
 */
class AuthApp : Application() {

    override fun onCreate() {
        super.onCreate()

        /** 🚀 Inicializar Koin */
        startKoin {
            androidContext(this@AuthApp)
            modules(
                dataStoreModule,
                fakeNetworkModule, //networkModule,
                authModule, // fakeAuthModule
            )
        }
    }
}
