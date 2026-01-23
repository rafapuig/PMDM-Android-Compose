package es.rafapuig.pmdm.clean.authentication.auth.data.fake

import es.rafapuig.pmdm.clean.authentication.auth.data.datasource.AuthTokenDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthTokenDataSource() : AuthTokenDataSource {

    private var token: String? = null

    override suspend fun saveToken(token: String) {
        this.token = token
    }

    override fun getToken(): Flow<String?> = flow {
        emit(token)
    }

    override suspend fun clear() {
        token = null
    }
}