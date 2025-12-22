package es.rafapuig.pmdm.states.todolist.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import es.rafapuig.pmdm.states.todolist.domain.model.Todo

class TodoListViewModel : ViewModel() {

    val todos = mutableStateListOf<Todo>()

    fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {
        todos.indexOfFirst { it.id == todo.id }.let { index ->
            if (index == -1) return@let
            todos[index] = todo.copy(isDone = isDone)
        }
    }

    fun onTodoDelete(todo: Todo) {
        todos.remove(todo)
    }

    fun onTodoAdd(taskName: String) {
        val lastId = todos.maxOfOrNull { it.id } ?: 0
        todos.add(Todo(id = lastId + 1, task = taskName))
    }

}