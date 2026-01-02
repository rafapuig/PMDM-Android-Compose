package es.rafapuig.pmdm.di.counter.common.domain.repositories

import kotlinx.coroutines.flow.Flow

interface CounterRepository {

    val counterFlow: Flow<Int>

    suspend fun increment()

    suspend fun decrement()

    suspend fun reset()

}