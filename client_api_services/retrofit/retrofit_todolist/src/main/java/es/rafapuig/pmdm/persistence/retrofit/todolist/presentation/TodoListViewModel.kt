package es.rafapuig.pmdm.persistence.retrofit.todolist.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.NetworkModule
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.TodosApiService
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers.toDto
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModel(private val api: TodosApiService) : ViewModel() {

    val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos = _todos.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            _todos.update { api.fetchAllTodos().map { dto -> dto.toDomain() } }
        }
    }

    fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {
        viewModelScope.launch {
            api.setTodoDone(todo.id, patch = TodoCompletedPatch(isCompleted = isDone))
            //loadTodos()
        }
    }

    fun onTodoDelete(todo: Todo) {
        viewModelScope.launch {
            api.deleteTodo(todo.id)
            //loadTodos()
        }
    }

    fun onTodoAdd(taskName: String) {
        viewModelScope.launch {
            api.addTodo(CreateTodoRequest(task = taskName))
            //loadTodos()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    //val app = this[APPLICATION_KEY] as TodoListApplication
                    //val dao = app.todosDatabase.todoDao()
                    TodoListViewModel(NetworkModule.apiService)
                }
            }
    }

}