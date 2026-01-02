package es.rafapuig.pmdm.di.app_container.counter

import android.content.Context
import es.rafapuig.pmdm.di.app_container.counter.data.CounterDataStore
import es.rafapuig.pmdm.di.app_container.counter.data.CounterRepositoryImpl
import es.rafapuig.pmdm.di.app_container.counter.data.counterDataStore
import es.rafapuig.pmdm.di.app_container.counter.domain.repositories.CounterRepository

class AppContainer(context: Context) {

    val counterRepository by lazy {
        val counterDataStore = CounterDataStore(context.applicationContext.counterDataStore)
        CounterRepositoryImpl(counterDataStore) as CounterRepository

    }

}