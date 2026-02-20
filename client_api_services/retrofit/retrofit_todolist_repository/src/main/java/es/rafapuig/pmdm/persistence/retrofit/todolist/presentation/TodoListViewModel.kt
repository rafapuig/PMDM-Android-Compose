package es.rafapuig.pmdm.persistence.retrofit.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.retrofit.todolist.TodoListApplication
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.repositories.TodosRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodosRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos = _todos.asStateFlow()

    init {
        observeIsLoading()
        observeTodos()
    }

    fun observeTodos() {
        viewModelScope.launch {
            repository.todos
                .catch { e ->
                    _errorMessage.value = e.message
                }
                .collect { todos ->
                    println("Todos: $todos")
                    _todos.update { todos }
                }
        }
    }

    fun observeIsLoading() {
        viewModelScope.launch {
            repository.isUpdating.collect { isLoading ->
                println("Cargando: $isLoading")
                if (isLoading) {
                    delay(500)
                }
                _isLoading.update { isLoading }
            }
        }
    }

    fun refresh() = viewModelScope.launch {
        println("Refrescando...")
        repository.refresh()
    }


    fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {

        viewModelScope.launch {
            try {
                repository.setTodoIsDone(todo, isDone)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun onTodoDelete(todo: Todo) {
        viewModelScope.launch {
            try {
                repository.deleteTodo(todo)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }


    fun onTodoAdd(taskName: String) {
        viewModelScope.launch {
            try {
                repository.addTodo(taskName)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun onErrorConsumed() {
        _errorMessage.value = null
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val app = this[APPLICATION_KEY] as TodoListApplication
                    val repository = app.repository
                    TodoListViewModel(repository)
                }
            }
    }

}