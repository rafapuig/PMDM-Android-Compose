package es.rafapuig.pmdm.client.counter.app_data_store

import android.app.Application
import es.rafapuig.pmdm.clean.counter.domain.RepositoryProvider
import es.rafapuig.pmdm.counter.data_store.data.CounterDataStore
import es.rafapuig.pmdm.counter.data_store.data.CounterRepositoryImpl
import es.rafapuig.pmdm.counter.data_store.data.counterDataStore

class WithDataStoreCounterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val counterDataStore = CounterDataStore(applicationContext.counterDataStore)
        val counterRepository = CounterRepositoryImpl(counterDataStore)

        RepositoryProvider.init(counterRepository)
    }

}