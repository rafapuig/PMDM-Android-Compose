package es.rafapuig.pmdm.clean.authentication.auth.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthLocalDataSource() : AuthLocalDataSource {

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