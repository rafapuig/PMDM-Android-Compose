package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.repository.AuthRepository
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.domain.repository.SessionRepository


class RegisterUseCase(
    private val repository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(username: String, password: String, email: String) {
        val token = repository.register(username, password, email)
        sessionRepository.saveToken(token)
    }
}
