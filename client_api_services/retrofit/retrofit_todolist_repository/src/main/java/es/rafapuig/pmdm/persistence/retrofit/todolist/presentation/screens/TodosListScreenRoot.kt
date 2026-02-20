package es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.screens

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.TodoListViewModel

@Preview
@Composable
fun TodosListScreenRoot(
    viewModel: TodoListViewModel = viewModel(factory = TodoListViewModel.Factory)
) {
    val todos by viewModel.todos.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()


    // Lanzamos snackbar cada vez que hay error
    LaunchedEffect(errorMessage) {
        errorMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
            viewModel.onErrorConsumed()
        }
    }

    TodoListScreen(
        todos = todos,
        isLoading = viewModel.isLoading.collectAsStateWithLifecycle().value,
        snackbarHostState = snackbarHostState,
        onTodoIsDoneChange = viewModel::onTodoIsDoneChange,
        onTodoDelete = viewModel::onTodoDelete,
        onTodoAdd = viewModel::onTodoAdd,
        onRefresh = viewModel::refresh
    )
}