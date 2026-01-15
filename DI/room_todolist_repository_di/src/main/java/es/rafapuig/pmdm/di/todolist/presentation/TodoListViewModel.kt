package es.rafapuig.pmdm.di.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.di.todolist.di.Dependencies
import es.rafapuig.pmdm.di.todolist.domain.model.Todo
import es.rafapuig.pmdm.di.todolist.domain.repositories.TodosRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(
    /** Se inyecta la dependencia del repositorio */
    private val repository: TodosRepository = Dependencies.get()
) : ViewModel() {

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

    /** Con inyección de dependencias no es necesario una factoria */
    /*companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val repository = Dependencies.get<TodosRepository>()
                    TodoListViewModel(repository)
                }
            }
    }*/

}