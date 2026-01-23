package es.rafapuig.pmdm.clean.authentication.auth.data.datasource

import kotlinx.coroutines.flow.Flow

interface AuthTokenDataSource {

    suspend fun saveToken(token: String)

    fun getToken(): Flow<String?>

    suspend fun clear()

}