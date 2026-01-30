package es.rafapuig.pmdm.clean.authentication.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorDto(
    val code: Int = 0,
    val message: String = ""
)
