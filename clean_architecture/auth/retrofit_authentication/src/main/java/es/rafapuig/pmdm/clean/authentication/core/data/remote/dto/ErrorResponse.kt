package es.rafapuig.pmdm.clean.authentication.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: Int = 400,
    val message: String = ""
)