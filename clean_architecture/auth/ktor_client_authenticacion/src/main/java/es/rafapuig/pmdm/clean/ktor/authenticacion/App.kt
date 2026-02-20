package es.rafapuig.pmdm.clean.ktor.authenticacion

import android.app.Application
import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.AuthInterceptor
import es.rafapuig.pmdm.clean.ktor.authenticacion.di.appModule
import es.rafapuig.pmdm.clean.ktor.authenticacion.di.coreModule
import io.ktor.client.HttpClient
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(coreModule, appModule))
        }

        val client = getKoin().get<HttpClient>()
        val interceptor = getKoin().get<AuthInterceptor>()
        interceptor.install(client)
    }
}
