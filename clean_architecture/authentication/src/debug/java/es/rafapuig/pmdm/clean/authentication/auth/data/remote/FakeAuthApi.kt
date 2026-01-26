package es.rafapuig.pmdm.clean.authentication.auth.data.remote

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterResponse
import kotlinx.coroutines.delay

class FakeAuthApi : AuthApi {

    private val users = mutableMapOf<String, String>(
        "test@test.com" to "1234"
    ) // email -> password


    override suspend fun login(request: LoginRequest): LoginResponse {
        delay(500)

        val password = users[request.email]
            ?: throw IllegalStateException("Usuario no existe")

        if (password != request.password) {
            throw IllegalStateException("Credenciales inválidas")
        }

        return LoginResponse(
            token = "fake-token-${request.email}"
        )
    }

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        delay(500)

        if (users.containsKey(request.email)) {
            throw IllegalStateException("Usuario ya existe")
        }

        users[request.email] = request.password

        return RegisterResponse(
            token = "fake-token-${request.email}"
        )
    }
}