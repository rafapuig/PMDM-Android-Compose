package es.rafapuig.pmdm.di.users.koin.compose.domain.usecase

import es.rafapuig.pmdm.di.users.koin.compose.domain.repository.UserRepository
import es.rafapuig.pmdm.di.users.koin.compose.domain.sampleUsers
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

@Factory
class LoadUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() {
        repository.addUsers(sampleUsers)
    }

}