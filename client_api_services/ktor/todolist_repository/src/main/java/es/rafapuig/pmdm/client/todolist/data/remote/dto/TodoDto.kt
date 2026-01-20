package es.rafapuig.pmdm.client.todolist.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 🔥 Ventajas de Kotlin Serialization con Retrofit
 *
 * ✅ Sin reflexión
 * ✅ Más rápido que Gson/Moshi
 * ✅ Totalmente type-safe
 * ✅ Multiplatform friendly
 * ✅ Compatible con Ktor si cambias backend
 */

@Serializable
data class TodoDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("task")
    val task: String = "",
    @SerialName("completed")
    val isCompleted: Boolean = false
)