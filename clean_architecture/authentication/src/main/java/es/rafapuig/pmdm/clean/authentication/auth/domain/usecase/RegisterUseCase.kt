package es.rafapuig.pmdm.clean.authentication.auth.domain.usecase

import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthError
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthException
import es.rafapuig.pmdm.clean.authentication.auth.domain.model.User
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): User {
        // Aquí puedes agregar lógica adicional antes de llamar al repositorio
        // Por ejemplo, validar los datos de entrada
        if (email.isBlank() || password.isBlank()) {
            throw AuthException(AuthError.EmptyCredentials)
        }
        return repository.register(email, password)
    }
}
