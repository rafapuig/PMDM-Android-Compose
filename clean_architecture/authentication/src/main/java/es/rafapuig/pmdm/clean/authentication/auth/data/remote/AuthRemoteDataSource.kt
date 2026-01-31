package es.rafapuig.pmdm.clean.authentication.auth.data.remote

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterResponse
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthError
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthException
import es.rafapuig.pmdm.clean.authentication.core.data.remote.dto.ErrorResponse
import es.rafapuig.pmdm.clean.authentication.core.data.remote.dto.parseApiError
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.HttpException

/**
 * Fuente de datos para la autenticación remota
 *
 * Depende de la API remota
 */
class AuthRemoteDataSource(
    private val api: AuthApi,
    private val json: Json
) /*: KoinComponent*/ {

    //val json : Json by inject() // = Json { ignoreUnknownKeys = true }

    suspend fun login(email: String, password: String): LoginResponse {

        val response =
            api.login(LoginRequest(email, password))

        if (response.isSuccessful) {
            return response.body()!!
        } else {

            /** 👉 Retrofit no intenta convertir el JSON de error automáticamente,
             * eso lo haces tú */

            val errorJson = response.errorBody()?.string()
            val element = json.parseToJsonElement(errorJson!!)
            val message = element.jsonObject["message"]?.jsonPrimitive?.content
            val code = element.jsonObject["code"]?.jsonPrimitive?.content

            throw AuthException(
                when (response.code()) {
                    401 -> AuthError.InvalidCredentials
                    404 -> AuthError.UserNotFound
                    else -> AuthError.Unknown
                }
            )
        }
    }


    suspend fun register(email: String, password: String): RegisterResponse {

        val response =
            api.register(RegisterRequest(email, password))

        if (response.isSuccessful) {
            return response.body()!!
        } else {

            val errorBody = response.errorBody()?.string()

            val apiError = errorBody?.let {
                json.decodeFromString<ErrorResponse>(it)
            }

            /*val apiError =
                response.parseApiError<ErrorResponse>(json)*/

            throw AuthException(
                when (response.code()) {
                    409 -> AuthError.UserAlreadyExists
                    else -> AuthError.Unknown
                }
            )
        }
    }
}