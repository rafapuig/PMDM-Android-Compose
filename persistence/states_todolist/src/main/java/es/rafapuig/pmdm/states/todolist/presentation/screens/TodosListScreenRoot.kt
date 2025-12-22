package es.rafapuig.pmdm.states.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.states.todolist.domain.model.Todo
import es.rafapuig.pmdm.states.todolist.domain.sampleTodos
import es.rafapuig.pmdm.states.todolist.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun TodosListScreenRoot() {

    val todos = remember {
        mutableStateListOf(*sampleTodos.toTypedArray())
    }

    val onTodoIsDoneChange: (Todo, Boolean) -> Unit = { todo, isDone ->
        todos.indexOfFirst { it.id == todo.id }.let { index ->
            if (index == -1) return@let
            todos[index] = todo.copy(isDone = isDone)
        }
    }

    val onTodoDelete: (Todo) -> Unit = { todo ->
        todos.remove(todo)
    }

    val onTodoAdd: (String) -> Unit = { taskName ->
        val lastId = todos.maxOfOrNull { it.id } ?: 0
        todos.add(Todo(id = lastId + 1, task = taskName))
    }

    PMDMComposeTheme {
        TodoListScreen(
            todos = todos,
            onTodoIsDoneChange = onTodoIsDoneChange,
            onTodoDelete = onTodoDelete,
            onTodoAdd = onTodoAdd
        )
    }

}