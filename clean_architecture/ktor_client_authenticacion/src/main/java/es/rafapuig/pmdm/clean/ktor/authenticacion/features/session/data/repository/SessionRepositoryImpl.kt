package es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.data.repository

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.data.SessionDataSource
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.domain.repository.SessionRepository


class SessionRepositoryImpl(private val dataSource: SessionDataSource) : SessionRepository {
    override fun getToken(): String? = dataSource.getToken()
    override fun saveToken(token: String) = dataSource.saveToken(token)
    override fun clearSession() = dataSource.clearSession()
}

