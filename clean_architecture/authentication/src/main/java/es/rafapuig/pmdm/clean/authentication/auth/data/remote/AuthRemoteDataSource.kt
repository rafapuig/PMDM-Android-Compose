package es.rafapuig.pmdm.clean.authentication.auth.data.remote

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterResponse
import es.rafapuig.pmdm.clean.authentication.core.data.remote.dto.ApiErrorDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException

/**
 * Fuente de datos para la autenticación remota
 *
 * Depende de la API remota
 */
class AuthRemoteDataSource (
    private val api: AuthApi
) : KoinComponent {

    val json : Json by inject() // = Json { ignoreUnknownKeys = true }

    suspend fun login(email: String, password: String): LoginResponse {
        try {
            return api.login(LoginRequest(email, password))
        } catch (e: HttpException) {
            if (e.code() == 400) {
                val errorJson = e.response()?.errorBody()?.string()
                val element = json.parseToJsonElement(errorJson!!)
                val message = element.jsonObject["message"]?.jsonPrimitive?.content

                throw  IllegalArgumentException(message)

                //throw InvalidCredentialsException()
            }
            throw e
        }
    }

    suspend fun register(email: String, password: String): RegisterResponse {
        try {
            return api.register(RegisterRequest(email, password))
        } catch (e: HttpException) {
            if (e.code() == 400) {
                val errorJson = e.response()?.errorBody()?.string()
                val apiError = json.decodeFromString<ApiErrorDto>(errorJson!!)
                throw  IllegalArgumentException(apiError.message)
            }
            throw e
        }

    }
}