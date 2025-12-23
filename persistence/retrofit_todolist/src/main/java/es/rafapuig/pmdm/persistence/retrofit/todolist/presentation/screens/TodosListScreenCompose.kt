package es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.NetworkModule
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.TodosApiService
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.retrofit.todolist.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun TodosListScreenCompose(
    api : TodosApiService = NetworkModule.apiService
) {

    val context = LocalContext.current

    //val scope = rememberCoroutineScope() //produce

    val todos by produceState(initialValue = emptyList<Todo>()) {
        value = api.fetchAllTodos().map { dto -> dto.toDomain() }
    }
/*
    val onTodoIsDoneChange: (Todo, Boolean) -> Unit = { todo, isDone ->
        scope.launch {
            dao.upsert(todo.toDatabase().copy(isDone = isDone))
        }
    }

    val onTodoDelete: (Todo) -> Unit = { todo ->
        scope.launch {
            dao.delete(todo.toDatabase())
        }
    }

    val onTodoAdd: (String) -> Unit = { taskName ->
        scope.launch {
            dao.upsert(Todo(task = taskName).toDatabase())
        }
    }*/

    PMDMComposeTheme {
        TodoListScreen(
            todos = todos,
            //onTodoIsDoneChange = onTodoIsDoneChange,
            //onTodoDelete = onTodoDelete,
            //onTodoAdd = onTodoAdd
        )
    }

}