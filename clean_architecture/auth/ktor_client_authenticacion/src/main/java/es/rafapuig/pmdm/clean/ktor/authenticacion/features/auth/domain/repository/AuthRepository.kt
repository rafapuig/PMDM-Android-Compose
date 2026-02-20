package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.repository

interface AuthRepository {
    suspend fun login(username: String, password: String): String
    suspend fun register(username: String, password: String, email: String): String
    suspend fun refreshToken(): String
    suspend fun logoutRemote()
}
