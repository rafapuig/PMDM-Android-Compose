package es.rafapuig.pmdm.compose.viewmodel.subscribers.domain.uses_cases

import es.rafapuig.pmdm.compose.viewmodel.subscribers.domain.repositories.SubscribersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.math.cos
import kotlin.time.Duration.Companion.milliseconds

class GetSubscribersUseCase(
    private val repository: SubscribersRepository
) {
    operator fun invoke(): Flow<Int> = repository.subscribers
}