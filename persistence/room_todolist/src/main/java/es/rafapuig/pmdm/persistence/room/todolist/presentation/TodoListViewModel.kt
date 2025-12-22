package es.rafapuig.pmdm.persistence.room.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.room.todolist.TodoListApplication
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodoDao
import es.rafapuig.pmdm.persistence.room.todolist.data.local.mappers.toDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.mappers.toDomain
import es.rafapuig.pmdm.persistence.room.todolist.domain.model.Todo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(private val dao: TodoDao) : ViewModel() {

    val todos = dao.getAllTodos().map { entities ->
        entities.map { entity -> entity.toDomain() }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {
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
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val app = this[APPLICATION_KEY] as TodoListApplication
                    val dao = app.todosDatabase.todoDao()
                    TodoListViewModel(dao)
                }
            }
    }

}