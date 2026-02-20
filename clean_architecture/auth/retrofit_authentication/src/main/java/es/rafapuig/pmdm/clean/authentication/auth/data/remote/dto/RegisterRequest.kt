package es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String
)
