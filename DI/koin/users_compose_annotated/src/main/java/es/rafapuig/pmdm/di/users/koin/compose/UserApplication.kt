package es.rafapuig.pmdm.di.users.koin.compose

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinApplication
import org.koin.core.context.startKoin

// MUY IMPORTANTE poner este import para que funcione Koin
import org.koin.ksp.generated.*

/**
 * Esta clase hay que especificarla en el manifest
 * en el elemento <application>
 * con el atributo android:name=".UserApplication"
 */

/**
 * We need to start Koin with our Android application.
 * With the @KoinApplication annotation,
 * Koin automatically discovers and loads all modules marked with @Configuration
 *
 * @KoinApplication - Automatically discovers all modules annotated with @Module and @Configuration
 * No need to manually call modules(AppModule().module) - modules are loaded automatically!
 * The import org.koin.ksp.generated.* import is required for generated Koin content
 * You only need to configure Android-specific settings like androidConte
 */

@KoinApplication
class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UserApplication)
            //modules(appModule) ya no es necesario !!!!
        }
    }
}