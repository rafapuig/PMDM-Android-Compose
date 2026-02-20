package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.repository.AuthRepository
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.domain.repository.SessionRepository


class LogoutUseCase(
    private val repository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        repository.logoutRemote()
        sessionRepository.clearSession()
    }
}
