package es.rafapuig.pmdm.clean.authentication.auth.data.remote

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApiConcrete.Messages.FAKE_TOKEN
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApiConcrete.Messages.INVALID_CREDENTIALS
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApiConcrete.Messages.INVALID_USER
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApiConcrete.Messages.USER_ALREADY_EXISTS
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterResponse
import es.rafapuig.pmdm.clean.authentication.core.data.remote.dto.ErrorResponse
import retrofit2.Response

class FakeAuthApiConcrete : BaseFakeApi(), AuthApi {

    object Messages {
        const val FAKE_TOKEN = "fake-token"
        const val INVALID_USER = "Usuario no existe"
        const val INVALID_CREDENTIALS = "Credenciales inválidas"
        const val USER_ALREADY_EXISTS = "Usuario ya existe"
    }

    private val users = mutableMapOf(
        "test@test.com" to "1234"
    )

    override suspend fun login(
        request: LoginRequest
    ): Response<LoginResponse> {

        val password = users[request.email]
            ?: return error(
                404,
                ErrorResponse.serializer(),
                ErrorResponse(404, INVALID_USER)
            )

        if (password != request.password) {
            return error(
                401,
                ErrorResponse.serializer(),
                ErrorResponse( 401, INVALID_CREDENTIALS)
            )
        }

        return success(
            LoginResponse(
                token = "$FAKE_TOKEN-${request.email}"
            )
        )
    }


    override suspend fun register(
        request: RegisterRequest
    ): Response<RegisterResponse> {

        if (users.containsKey(request.email)) {
            return error(
                409,
                ErrorResponse.serializer(),
                ErrorResponse(409, USER_ALREADY_EXISTS)
            )
        }

        users[request.email] = request.password

        return success(
            RegisterResponse(
                token = "$FAKE_TOKEN-${request.email}"
            )
        )
    }
}