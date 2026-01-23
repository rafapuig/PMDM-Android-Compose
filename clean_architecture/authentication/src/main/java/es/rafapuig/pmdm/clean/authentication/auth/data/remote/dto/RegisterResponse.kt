package es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id: String = "",
    val email: String = "",
    val token: String = ""
)
