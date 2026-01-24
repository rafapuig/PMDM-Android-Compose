package es.rafapuig.pmdm.clean.authentication.auth.data.local

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {

    suspend fun saveToken(token: String)

    fun getToken(): Flow<String?>

    suspend fun clear()

}