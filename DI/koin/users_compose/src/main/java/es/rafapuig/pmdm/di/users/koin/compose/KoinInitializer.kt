package es.rafapuig.pmdm.di.users.koin.compose

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import es.rafapuig.pmdm.di.users.koin.compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class KoinInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Log.d("Startup", "KoinInitializer called!")
        startKoin {
            androidContext(context)
            modules(appModule)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        emptyList()
}
