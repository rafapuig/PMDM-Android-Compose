package es.rafapuig.pmdm.di.counter.common.data

import es.rafapuig.pmdm.di.counter.common.domain.repositories.CounterRepository
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