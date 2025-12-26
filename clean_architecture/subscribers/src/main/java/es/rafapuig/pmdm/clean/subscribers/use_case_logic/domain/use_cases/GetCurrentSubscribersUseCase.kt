package es.rafapuig.pmdm.clean.subscribers.use_case_logic.domain.use_cases

import es.rafapuig.pmdm.clean.subscribers.core.utils.generateSubscribersFlow
import kotlinx.coroutines.flow.Flow

object GetCurrentSubscribersUseCase {

    operator fun invoke(
        period: Float = 25f, // segundos para pasar de 0 a max y volver a 0
        maxSubscribers: Int = 20_000 // numero maximo de subscriptores a alcanzar
    ): Flow<Int> = generateSubscribersFlow(
        maxSubscribers = maxSubscribers,
        period = period
    )
}