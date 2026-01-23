package es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * 👉 Regla clave:
 * El DTO refleja EXACTAMENTE lo que devuelve el backend.
 */
@Serializable
data class LoginResponse(
    val id: String,
    val email: String,
    val token: String
)

