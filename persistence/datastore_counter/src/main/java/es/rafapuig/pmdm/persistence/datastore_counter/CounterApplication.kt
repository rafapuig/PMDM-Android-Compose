package es.rafapuig.pmdm.persistence.datastore_counter

import android.app.Application
import es.rafapuig.pmdm.persistence.datastore_counter.data.CounterDataStore
import es.rafapuig.pmdm.persistence.datastore_counter.data.counterDataStore

class CounterApplication : Application() {

    //val counterDataStore by lazy { CounterDataStore(this.counterDataStore) }

    lateinit var counterDataStore: CounterDataStore

    override fun onCreate() {
        super.onCreate()
        counterDataStore = CounterDataStore(applicationContext.counterDataStore)
    }

}