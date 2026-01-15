package es.rafapuig.pmdm.di.users.koin.compose.domain.usecase

import es.rafapuig.pmdm.di.users.koin.compose.domain.repository.UserRepository
import es.rafapuig.pmdm.di.users.koin.compose.domain.sampleUsers

class LoadUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() {
        repository.addUsers(sampleUsers)
    }

}