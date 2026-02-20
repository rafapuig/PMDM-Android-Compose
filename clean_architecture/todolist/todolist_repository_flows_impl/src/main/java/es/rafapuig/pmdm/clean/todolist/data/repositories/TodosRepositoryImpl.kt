package es.rafapuig.pmdm.clean.todolist.data.repositories

import es.rafapuig.pmdm.clean.todolist.domain.model.Todo
import es.rafapuig.pmdm.clean.todolist.domain.repositories.TodosRepository
import es.rafapuig.pmdm.clean.todolist.domain.sampleTodos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodosRepositoryImpl : TodosRepository {

    private val _todos : MutableStateFlow<List<Todo>> = MutableStateFlow(sampleTodos)
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()


    override fun getTodos(): Flow<List<Todo>> {
        return todos
    }

    override suspend fun addTodo(todo: Todo) {
        _todos.value += todo
    }

    override suspend fun updateTodo(todo: Todo) {
        _todos.value = _todos.value.map { if (it.id == todo.id) todo else it }
    }

    override suspend fun deleteTodo(todo: Todo) {
        _todos.value = _todos.value.filter { it.id != todo.id }
    }

}