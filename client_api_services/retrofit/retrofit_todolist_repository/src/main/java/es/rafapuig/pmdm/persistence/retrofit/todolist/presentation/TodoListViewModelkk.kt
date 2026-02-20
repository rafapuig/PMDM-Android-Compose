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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModelkk(private val repository: TodosRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = repository.isUpdating
        .onEach { delay(500) }// _isLoading.asStateFlow() //repository.isLoading // _isLoading.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()


    val todos = repository.todos
        //.withDelayedLoading(_isLoading, 50)
        /*.onStart {
            delay(250.milliseconds)
            _isLoading.value = true
        }
        .onEach { _isLoading.value = false }*/
        .catch { e ->
            //_isLoading.value = false
            _errorMessage.value = e.message
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private suspend fun withDelayedLoading(
        delayMillis: Long = 500,
        block: suspend () -> Unit
    ) {
        coroutineScope {

            val job = launch {
                delay(delayMillis)
                _isLoading.update { true }
            }
            launch {
                try {
                    delay(1000)
                    block()
                } finally {
                    println("Cancelando el job...")
                    job.cancel()
                }
            }
        }
        println("Finalizando el scope...")
        _isLoading.update { false }
    }


    fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {

        viewModelScope.launch {
            coroutineScope {
                val isLoadingJob = launch {
                    delay(500)
                    _isLoading.update { true }
                }
                launch {
                    try {
                        delay(1000)
                        repository.setTodoIsDone(todo, isDone)
                    } catch (e: Exception) {
                        _errorMessage.value = e.message
                    }
                    _isLoading.update { false }
                    isLoadingJob.cancel()
                }
            }
            _isLoading.update { false }
        }
    }

    fun onTodoDelete(todo: Todo) {
        viewModelScope.launch {
            withDelayedLoading {
                //delay(1000)
                repository.deleteTodo(todo)
            }
        }
    }

    fun onTodoDelete2(todo: Todo) {
        viewModelScope.launch {
            val isLoadingJob = launch {
                delay(500)
                _isLoading.update { true }
            }
            launch {
                try {
                    delay(1000)
                    repository.deleteTodo(todo)
                } catch (e: Exception) {
                    _errorMessage.value = e.message
                } finally {
                    isLoadingJob.cancel()
                    _isLoading.value = false
                }
            }
        }
    }

    fun onTodoAdd(taskName: String) {
        viewModelScope.launch {
            //_isLoading.value = true
            try {
                repository.addTodo(taskName)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            //_isLoading.value = false
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
                    TodoListViewModelkk(repository)
                }
            }
    }

}