package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.data.datasource

import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.LoginResource
import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.LoginResponse
import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.LogoutResource
import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.RefreshResponse
import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.RefreshTokenResource
import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.RegisterResource
import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.RegisterResponse

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.post


class AuthRemoteDataSourceImpl(private val client: HttpClient) : AuthRemoteDataSource {
    override suspend fun login(username: String, password: String) =
        client.post(LoginResource(username, password)).body<LoginResponse>().token

    override suspend fun register(username: String, password: String, email: String) =
        client.post(RegisterResource(username, password, email)).body<RegisterResponse>().token

    override suspend fun refreshToken() =
        client.post(RefreshTokenResource()).body<RefreshResponse>().token

    override suspend fun logoutRemote() {
        client.post(LogoutResource())
    }
}