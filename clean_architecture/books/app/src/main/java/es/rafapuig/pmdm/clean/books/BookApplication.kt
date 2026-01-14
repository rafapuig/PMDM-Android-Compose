package es.rafapuig.pmdm.clean.books

import android.app.Application
import es.rafapuig.pmdm.clean.books.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BookApplication)
            modules(appModule)
        }

    }
}