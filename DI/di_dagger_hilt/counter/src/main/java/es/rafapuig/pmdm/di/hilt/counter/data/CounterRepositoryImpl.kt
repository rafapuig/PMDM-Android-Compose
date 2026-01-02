package es.rafapuig.pmdm.di.hilt.counter.data

import es.rafapuig.pmdm.di.hilt.counter.domain.repositories.CounterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** https://developer.android.com/training/dependency-injection/hilt-android#define-bindings */

class CounterRepositoryImpl @Inject constructor(
    private val dataStore: CounterDataStore
) : CounterRepository {

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