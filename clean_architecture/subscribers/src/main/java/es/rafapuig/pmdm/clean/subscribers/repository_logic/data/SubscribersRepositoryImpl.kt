package es.rafapuig.pmdm.clean.subscribers.repository_logic.data

import es.rafapuig.pmdm.clean.subscribers.core.utils.generateSubscribersFlow
import es.rafapuig.pmdm.clean.subscribers.repository_logic.domain.repositories.SubscribersRepository
import kotlinx.coroutines.flow.Flow

class SubscribersRepositoryImpl : SubscribersRepository {

    override val subscribers: Flow<Int> = generateSubscribersFlow(
        maxSubscribers = 10_000,
        period = 20f
    )

}