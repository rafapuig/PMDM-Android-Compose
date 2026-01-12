package es.rafapuig.pmdm.service_locator.counter

import android.app.Application
import es.rafapuig.pmdm.service_locator.counter.data.CounterDataStore
import es.rafapuig.pmdm.service_locator.counter.data.CounterRepositoryImpl
import es.rafapuig.pmdm.service_locator.counter.data.counterDataStore
import es.rafapuig.pmdm.service_locator.counter.domain.repositories.CounterRepository

object CounterServiceLocator {

    lateinit var context: Application

    fun configure(context: Application) {
        CounterServiceLocator.context = context
    }

    val counterRepository: CounterRepository by lazy {
        check(::context.isInitialized) { "Application context not..... initialized" }
        val dataStore = CounterDataStore(context.applicationContext.counterDataStore)
        CounterRepositoryImpl(dataStore) as CounterRepository
    }
}