package es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.NetworkModule
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.TodosApiService
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers.toDto
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.retrofit.todolist.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.launch

@Preview
@Composable
fun TodosListScreenCompose(
    api: TodosApiService = NetworkModule.apiService
) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope() //produce

    /*val todos by produceState(initialValue = emptyList<Todo>()) {
        value = api.fetchAllTodos().map { dto -> dto.toDomain() }
    }*/

    var todos by remember { mutableStateOf<List<Todo>>(emptyList()) }

    suspend fun fetchTodos(): List<Todo> {
        return api.fetchAllTodos().map { dto -> dto.toDomain() }
    }


    LaunchedEffect(Unit) {
        todos = fetchTodos()
    }

    val onTodoIsDoneChange: (Todo, Boolean) -> Unit = { todo, isDone ->
        scope.launch {
            api.setTodoDone(todo.id, TodoCompletedPatch(isCompleted = isDone))
            todos = fetchTodos()
        }
    }

    val onTodoDelete: (Todo) -> Unit = { todo ->
        scope.launch {
            api.deleteTodo(todo.id)
            todos = fetchTodos()
        }
    }

    val onTodoAdd: (String) -> Unit = { taskName ->
        scope.launch {
            api.addTodo(CreateTodoRequest(task = taskName))
            todos = fetchTodos()
        }
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