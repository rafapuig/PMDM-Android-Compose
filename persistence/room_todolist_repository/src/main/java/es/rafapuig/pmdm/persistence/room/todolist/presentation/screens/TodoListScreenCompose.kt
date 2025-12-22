package es.rafapuig.pmdm.persistence.room.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodosRepositoryLocalImpl
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.getDatabaseInstance
import es.rafapuig.pmdm.persistence.room.todolist.domain.repositories.TodosRepository
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun TodoListScreenCompose() {

    val context = LocalContext.current

    val repository = remember {
        val factory = InMemoryTodosDatabaseFactory()
        val db = context.getDatabaseInstance(factory)
        val repository = TodosRepositoryLocalImpl(db.todoDao())
        repository as TodosRepository
    }

    val todos by repository.todos
        .collectAsStateWithLifecycle(initialValue = emptyList())

    val scope = rememberCoroutineScope()

    PMDMComposeTheme {
        TodoListScreen(
            todos = todos,
            onTodoIsDoneChange = { todo, isDone ->
                scope.launch {
                    repository.setTodoIsDone(todo, isDone)
                }
            },
            onTodoDelete = { todo ->
                scope.launch {
                    repository.deleteTodo(todo)
                }
            },
            onTodoAdd = { taskName ->
                scope.launch {
                    repository.addTodo(taskName)
                }
            }
        )
    }

}