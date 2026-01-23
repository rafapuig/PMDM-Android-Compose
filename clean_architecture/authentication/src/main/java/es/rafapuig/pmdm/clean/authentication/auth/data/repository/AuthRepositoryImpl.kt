package es.rafapuig.pmdm.clean.authentication.auth.data.repository

import es.rafapuig.pmdm.clean.authentication.auth.data.local.AuthLocalDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.mapper.toDomain
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.AuthRemoteDataSource
import es.rafapuig.pmdm.clean.authentication.auth.domain.model.User
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first

class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource,
    private val local: AuthLocalDataSource
) : AuthRepository {

    override suspend fun login(email: String, password: String): User {
        val response = remote.login(email, password)
        local.saveToken(response.token)
        return response.toDomain()
    }

    override suspend fun register(email: String, password: String): User {
        val response = remote.register(email, password)
        local.saveToken(response.token)
        return response.toDomain()
    }

    override suspend fun logout() {
        local.clear()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return local.getToken().first() != null
    }
}
