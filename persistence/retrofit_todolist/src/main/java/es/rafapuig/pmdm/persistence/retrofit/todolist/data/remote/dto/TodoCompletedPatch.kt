package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoCompletedPatch(
    @SerialName("completed")
    val isCompleted: Boolean
)
