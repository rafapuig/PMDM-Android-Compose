package es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.domain.repository

interface SessionRepository {
    fun getToken(): String?
    fun saveToken(token: String)
    fun clearSession()
}
