package es.rafapuig.pmdm.clean.todolist.domain.repositories

import es.rafapuig.pmdm.clean.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodosRepository {

    fun getTodos(): Flow<List<Todo>>

    suspend fun addTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

}