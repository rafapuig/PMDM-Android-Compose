package es.rafapuig.pmdm.client.todolist.presentation.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.client.todolist.data.TodoRepositoryRemoteImpl
import es.rafapuig.pmdm.client.todolist.data.remote.NetworkModule
import es.rafapuig.pmdm.client.todolist.data.remote.TodoApiService
import es.rafapuig.pmdm.client.todolist.domain.model.Todo
import es.rafapuig.pmdm.client.todolist.domain.repositories.TodoRepository
import es.rafapuig.pmdm.client.todolist.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun TodosListScreenCompose(
    api: TodoApiService = NetworkModule.apiService
) {

    val repository = remember {
        val repository = TodoRepositoryRemoteImpl(api)
        repository as TodoRepository
    }

    val todos by repository.todos
        .collectAsStateWithLifecycle(initialValue = emptyList())

    val isUpdating by repository.isUpdating
        .collectAsStateWithLifecycle(initialValue = false)

    var errorMessage by remember {mutableStateOf<String?>(null)}

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(errorMessage) {
        errorMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                //duration = SnackbarDuration.Short
            )
        }
        errorMessage = null
    }


    val scope = rememberCoroutineScope()

    val onTodoIsDoneChange: (Todo, Boolean) -> Unit = { todo, isDone ->
        scope.launch {
            try {
                repository.setTodoIsDone(todo, isDone)
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    val onTodoDelete: (Todo) -> Unit = { todo ->
        scope.launch {
            try {
                repository.deleteTodo(todo)
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    val onTodoAdd: (String) -> Unit = { taskName ->
        scope.launch {
            try {
                repository.addTodo(taskName)
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    PMDMComposeTheme {
        TodoListScreen(
            todos = todos,
            isLoading = isUpdating,
            onTodoIsDoneChange = onTodoIsDoneChange,
            onTodoDelete = onTodoDelete,
            onTodoAdd = onTodoAdd,
            onRefresh = {
                println("Refrescando... desde compose")
                scope.launch {
                    repository.refresh()
                }
            }
        )
    }

}