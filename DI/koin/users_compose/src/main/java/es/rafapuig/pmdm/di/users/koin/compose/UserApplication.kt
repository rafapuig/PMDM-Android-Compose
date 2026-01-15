package es.rafapuig.pmdm.di.users.koin.compose

import android.app.Application
import es.rafapuig.pmdm.di.users.koin.compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Esta clase hay que especificarla en el manifest
 * en el elemento <application>
 * con el atributo android:name=".UserApplication"
 */
class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UserApplication)
            modules(appModule)
        }
    }
}