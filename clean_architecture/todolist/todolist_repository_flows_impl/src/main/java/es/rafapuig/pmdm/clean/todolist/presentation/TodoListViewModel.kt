package es.rafapuig.pmdm.clean.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.clean.todolist.TodosApplication
import es.rafapuig.pmdm.clean.todolist.domain.model.Todo
import es.rafapuig.pmdm.clean.todolist.domain.repositories.TodosRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodosRepository) : ViewModel() {

    val todos = repository.getTodos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {
        viewModelScope.launch {
            repository.updateTodo(todo.copy(isDone = isDone))
        }
    }

    fun onTodoDelete(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun onTodoAdd(taskName: String) {
        viewModelScope.launch {
            repository.addTodo(Todo(task = taskName))
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as TodosApplication
                TodoListViewModel(application.repository)
            }
        }
    }

}