package es.rafapuig.pmdm.clean.authentication.auth.data.remote

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * API remota de autenticación (Retrofit)
 */
interface AuthApi {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}

