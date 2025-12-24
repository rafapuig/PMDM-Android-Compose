package es.rafapuig.pmdm.persistence.retrofit.todolist.domain.repositories


import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface TodosRepository {

    val todos : Flow<List<Todo>>

    val isUpdating: Flow<Boolean> get() = flowOf(false)

    suspend fun setTodoIsDone(todo: Todo, isDone: Boolean)

    suspend fun deleteTodo(todo: Todo)

    suspend fun addTodo(task: String)

    suspend fun refresh() = Unit
}