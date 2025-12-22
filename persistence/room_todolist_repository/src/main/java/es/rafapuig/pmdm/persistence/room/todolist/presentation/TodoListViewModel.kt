package es.rafapuig.pmdm.persistence.room.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.room.todolist.TodoListApplication
import es.rafapuig.pmdm.persistence.room.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.room.todolist.domain.repositories.TodosRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodosRepository) : ViewModel() {

    val todos = repository.todos
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {
        viewModelScope.launch {
            repository.setTodoIsDone(todo, isDone)
        }
    }

    fun onTodoDelete(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun onTodoAdd(taskName: String) {
        viewModelScope.launch {
            repository.addTodo(taskName)
        }
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