package es.rafapuig.pmdm.clean.authentication.auth.data.remote

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApi.Messages.INVALID_CREDENTIALS
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApi.Messages.INVALID_USER
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApi.Messages.USER_ALREADY_EXISTS
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterResponse
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeAuthApi : AuthApi {

    object Messages {
        const val FAKE_TOKEN = "fake-token"
        const val INVALID_USER = "Usuario no existe"
        const val INVALID_CREDENTIALS = "Credenciales inválidas"
        const val USER_ALREADY_EXISTS = "Usuario ya existe"
    }

    private val users = mutableMapOf<String, String>(
        "test@test.com" to "1234"
    ) // email -> password


    override suspend fun login(
        request: LoginRequest
    ): Response<LoginResponse> {

        delay(500)

        val password = users[request.email] ?: return Response.error(
            404,
            apiErrorJson(INVALID_USER, 404).toErrorBody()
        )

        if (password != request.password)
            return Response.error(
                401,
                apiErrorJson(INVALID_CREDENTIALS, 401).toErrorBody()
            )

        return Response.success(
            LoginResponse(
                token = "fake-token-${request.email}"
            )
        )
    }

    override suspend fun register(
        request: RegisterRequest
    ): Response<RegisterResponse> {

        delay(500)

        if (users.containsKey(request.email)) {
            return Response.error(
                409,
                apiErrorJson(USER_ALREADY_EXISTS, 409).toErrorBody()
            )
        }

        users[request.email] = request.password

        return Response.success(
            RegisterResponse(
                token = "fake-token-${request.email}"
            )
        )
    }

    fun apiErrorJson(message: String, code: Int) =
        """
    {
      "message": "$message",
      "code": $code
    }
    """.trimIndent()



    fun String.toErrorBody(): ResponseBody =
        toResponseBody("application/json".toMediaType())

}