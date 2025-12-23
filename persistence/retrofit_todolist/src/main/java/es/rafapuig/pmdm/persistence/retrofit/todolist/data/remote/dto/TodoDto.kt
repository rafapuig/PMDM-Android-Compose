package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoDto(
    @SerialName("done")
    val done: Boolean = false,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("title")
    val title: String = ""
)