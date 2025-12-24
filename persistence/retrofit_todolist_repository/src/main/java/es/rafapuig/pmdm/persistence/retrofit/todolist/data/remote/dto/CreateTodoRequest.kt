package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateTodoRequest(
    val task: String
)
