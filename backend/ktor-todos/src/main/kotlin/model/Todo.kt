package es.rafapuig.pmdm.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoDto(
    val id: Int,
    val task: String,
    val completed: Boolean = false
)

@Serializable
data class CreateTodoRequest(
    val task: String
)

@Serializable
data class UpdateTodoRequest(
    val completed: Boolean
)
