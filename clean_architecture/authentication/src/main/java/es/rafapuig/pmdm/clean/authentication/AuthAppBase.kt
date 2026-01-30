package es.rafapuig.pmdm.clean.authentication

import android.app.Application
import es.rafapuig.pmdm.clean.authentication.di.appModule
import es.rafapuig.pmdm.clean.authentication.di.backendModules
import es.rafapuig.pmdm.clean.authentication.di.commonAuthModule
import es.rafapuig.pmdm.clean.authentication.di.platformNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * No olvides registrar la Application en el AndroidManifest.xml 👇
 */
abstract class AuthAppBase : Application() {

    override fun onCreate() {
        super.onCreate()

        /** 🚀 Inicializar Koin */
        startKoin {
            androidContext(this@AuthAppBase)

            modules(platformNetworkModule())
            modules(backendModules)
            modules(commonAuthModule)
            modules(appModule)
        }

    }
}
