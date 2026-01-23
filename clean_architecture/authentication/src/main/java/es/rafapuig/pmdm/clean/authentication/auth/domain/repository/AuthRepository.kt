package es.rafapuig.pmdm.clean.authentication.auth.domain.repository

import es.rafapuig.pmdm.clean.authentication.auth.domain.model.User

/**
 * El contrato para las implementaciones de la capa de datos
 */

interface AuthRepository {
    suspend fun login(email: String, password: String): User
    suspend fun register(email: String, password: String): User
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
}