package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.repository.AuthRepository
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.domain.repository.SessionRepository

class LoginUseCase(
    private val repository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(username: String, password: String) {
        val token = repository.login(username, password)
        sessionRepository.saveToken(token)
    }
}
