package es.rafapuig.pmdm.clean.authentication.auth.domain.usecase

import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository

/**
 * 👉
 * El dominio solo pregunta si hay sesión
 * No sabe de tokens, DataStore, Flow, etc.
 */

class IsUserLoggedInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.isUserLoggedIn()
    }
}
