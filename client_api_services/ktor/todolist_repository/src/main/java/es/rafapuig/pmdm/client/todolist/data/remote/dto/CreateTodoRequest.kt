package es.rafapuig.pmdm.client.todolist.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateTodoRequest(
    val task: String
)
