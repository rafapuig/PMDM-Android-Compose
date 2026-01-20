package es.rafapuig.pmdm.client.todolist.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoCompletedPatch(
    @SerialName("completed")
    val isCompleted: Boolean
)
