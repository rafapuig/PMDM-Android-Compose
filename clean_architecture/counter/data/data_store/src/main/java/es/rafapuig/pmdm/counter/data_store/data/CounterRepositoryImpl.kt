package es.rafapuig.pmdm.counter.data_store.data

import es.rafapuig.pmdm.clean.counter.domain.repositories.CounterRepository
import kotlinx.coroutines.flow.Flow

class CounterRepositoryImpl(
    private val dataStore: CounterDataStore
): CounterRepository {

    override val counterFlow: Flow<Int>
        get() = dataStore.counterFlow

    override suspend fun increment() {
        dataStore.increment()
    }

    override suspend fun decrement() {
        dataStore.decrement()
    }

    override suspend fun reset() {
        dataStore.reset()
    }
}