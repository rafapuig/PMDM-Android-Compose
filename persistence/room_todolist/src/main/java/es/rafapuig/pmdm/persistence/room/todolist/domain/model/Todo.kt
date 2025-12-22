package es.rafapuig.pmdm.persistence.room.todolist.domain.model

/**
 * Clase del modelo del dominio.
 */
data class Todo(
    val id: Int = 0,
    val task : String,
    val isDone: Boolean = false
)
