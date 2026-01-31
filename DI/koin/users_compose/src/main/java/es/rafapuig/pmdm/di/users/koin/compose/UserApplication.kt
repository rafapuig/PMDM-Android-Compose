package es.rafapuig.pmdm.di.users.koin.compose

import android.app.Application
import android.util.Log
import es.rafapuig.pmdm.di.users.koin.compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatformTools

/**
 * Esta clase hay que especificarla en el manifest
 * en el elemento <application>
 * con el atributo android:name=".UserApplication"
 */
class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Solo por seguridad si Startup falla
        if (GlobalContext.getOrNull() == null) {
            Log.d("Startup", "KoinInitializer called from Application!")
            /** Iniciamos Koin */
            startKoin {

                /** Obtenemos el contexto de la aplicación  para inyectarlo */
                androidContext(this@UserApplication)

                /** Cargamos el modulo appModule con sus definiciones */
                modules(appModule)
            }
        }
    }
}