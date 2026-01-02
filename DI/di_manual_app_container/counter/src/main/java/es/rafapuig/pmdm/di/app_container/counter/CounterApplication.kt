package es.rafapuig.pmdm.di.app_container.counter

import android.app.Application

class CounterApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(applicationContext)
    }

}
