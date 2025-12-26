package es.rafapuig.pmdm.clean.subscribers.repository_logic.domain.use_cases

import es.rafapuig.pmdm.clean.subscribers.repository_logic.domain.repositories.SubscribersRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentSubscribersUseCase(
    private val repository: SubscribersRepository
) {
    operator fun invoke(): Flow<Int> = repository.subscribers
}