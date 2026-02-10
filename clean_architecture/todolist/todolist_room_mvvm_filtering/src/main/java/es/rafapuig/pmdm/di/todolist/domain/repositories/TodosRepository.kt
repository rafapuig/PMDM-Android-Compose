package es.rafapuig.pmdm.di.todolist.domain.repositories


import es.rafapuig.pmdm.di.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodosRepository {

    val todos : Flow<List<Todo>>

    fun searchTodos(query: String) : Flow<List<Todo>>

    suspend fun setTodoIsDone(todo: Todo, isDone: Boolean)

    suspend fun deleteTodo(todo: Todo)

    suspend fun addTodo(task: String)
}