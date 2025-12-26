package es.rafapuig.pmdm.clean.subscribers.repository_logic.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SubscribersRepository {
    val subscribers: Flow<Int>
}