package es.rafapuig.pmdm.service_locator.counter

import android.app.Application

class CounterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CounterServiceLocator.configure(this)
    }

}
