package es.rafapuig.pmdm.clean.authentication.auth.domain.usecase

import es.rafapuig.pmdm.clean.authentication.auth.domain.model.User
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository


/**
 * Cada acción del negocio es un UseCase.
 * 👉 Ventaja: el login se puede testear sin Android, sin red, sin nada
 */
class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): User {
        // Validacion
        if (email.isBlank() || password.isBlank()) {
            throw IllegalArgumentException("Email or password is empty")
        }
        return authRepository.login(email, password)
    }
}
