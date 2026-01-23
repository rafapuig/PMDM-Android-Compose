package es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 👉 Regla clave:
 * El DTO refleja EXACTAMENTE lo que devuelve el backend.
 */
@Serializable
data class LoginResponse(
    @SerialName("user_id")
    val id: String = "",

    @SerialName("user_email")
    val email: String = "",

    @SerialName("access_token")
    val token: String = ""
)

