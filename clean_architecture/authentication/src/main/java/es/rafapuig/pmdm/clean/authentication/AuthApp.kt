package es.rafapuig.pmdm.clean.authentication

import android.app.Application
import es.rafapuig.pmdm.clean.authentication.di.authRepositoryModule
import es.rafapuig.pmdm.clean.authentication.di.commonAuthModule
import es.rafapuig.pmdm.clean.authentication.di.dataStoreModule
import es.rafapuig.pmdm.clean.authentication.di.fakeAuthRepositoryModule
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

            when (BuildConfig.BUILD_TYPE) {
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
            }
        }
    }
}
