package es.rafapuig.pmdm.compose.viewmodel.subscribers.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SubscribersRepository {
    val subscribers : Flow<Int>
}