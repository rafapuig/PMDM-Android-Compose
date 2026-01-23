package es.rafapuig.pmdm.clean.authentication.auth.domain.usecase

import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository

/**
 * No devuelve nada
 * No sabe cómo se hace logout
 * Solo ejecuta la regla de negocio
 */
class LogoutUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() {
        repository.logout()
    }
}
