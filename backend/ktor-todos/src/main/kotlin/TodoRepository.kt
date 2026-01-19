package es.rafapuig.pmdm

import es.rafapuig.pmdm.model.TodoDto
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.sampleTodos

class TodoRepository {

    private val todos = mutableListOf<TodoDto>(
        *sampleTodos.toTypedArray()
    )
    private var nextId = (todos.maxOfOrNull { it.id } ?: 0) + 1

    fun getAll(): List<TodoDto> = todos

    fun add(title: String): TodoDto {
        val todo = TodoDto(nextId++, title, false)
        todos.add(todo)
        return todo
    }

    fun update(id: Int, done: Boolean): TodoDto? {
        val index = todos.indexOfFirst { it.id == id }
        if (index == -1) return null

        val updated = todos[index].copy(completed = done)
        todos[index] = updated
        return updated
    }

    fun delete(id: Int): Boolean =
        todos.removeIf { it.id == id }
}
