package es.rafapuig.pmdm.persistence.room.tvseries

import android.app.Application
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.initializers.AppDataInitializer
import es.rafapuig.pmdm.persistence.room.tvseries.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.getKoin
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class TvSeriesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TvSeriesApp)
            modules(appModule)
        }
        // ✅ Forma correcta fuera de Compose
        val initializer = GlobalContext.get().get<AppDataInitializer>()
        initializer.initialize()
    }
}