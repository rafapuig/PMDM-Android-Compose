package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.data.repository

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.data.datasource.AuthRemoteDataSource
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(private val remote: AuthRemoteDataSource) : AuthRepository {
    override suspend fun login(username: String, password: String) = remote.login(username, password)
    override suspend fun register(username: String, password: String, email: String) = remote.register(username, password, email)
    override suspend fun refreshToken() = remote.refreshToken()
    override suspend fun logoutRemote() = remote.logoutRemote()
}
