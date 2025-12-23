package es.rafapuig.pmdm.persistence.retrofit.todolist.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.NetworkModule
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.TodosApiService
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(private val api: TodosApiService) : ViewModel() {

    init {
        loadTodos()
    }

    val todos = mutableStateListOf<Todo>()

    private fun loadTodos() {
        viewModelScope.launch {
            todos.addAll(
                api.fetchAllTodos().map { dto -> dto.toDomain() })
        }
    }


    /*fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {
        viewModelScope.launch {
            dao.upsert(todo.toDatabase().copy(isDone = isDone))
        }
    }

    fun onTodoDelete(todo: Todo) {
        viewModelScope.launch {
            dao.delete(todo.toDatabase())
        }
    }

    fun onTodoAdd(taskName: String) {
        viewModelScope.launch {
            dao.upsert(Todo(task = taskName).toDatabase())
        }
    }*/

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