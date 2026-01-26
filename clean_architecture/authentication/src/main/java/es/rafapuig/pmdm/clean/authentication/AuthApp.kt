package es.rafapuig.pmdm.clean.authentication

import android.app.Application
import es.rafapuig.pmdm.clean.authentication.di.appModule
import es.rafapuig.pmdm.clean.authentication.di.backendModules
import es.rafapuig.pmdm.clean.authentication.di.commonAuthModule
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

            modules(backendModules)
            modules(commonAuthModule)
            modules(appModule)

            /*when (BuildConfig.BUILD_TYPE) {
                "staging" ->
                    modules(
                        dataStoreModule,
                        fakeNetworkModule,
                        authRepositoryModule,
                        commonAuthModule
                    )

                "debug" ->
                    modules(
                        fakeAuthRepositoryModule,
                        commonAuthModule
                    )

                else ->
                    modules(
                        dataStoreModule,
                        networkModule,
                        authRepositoryModule,
                        commonAuthModule
                    )
            }*/
        }
    }
}
