package es.rafapuig.pmdm.di.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.di.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.di.todolist.ui.theme.PMDMComposeTheme
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun TodoListScreenRoot(viewModel: TodoListViewModel = koinViewModel() /* viewModel() */) {

    /** No necesita valor inicial porque todos es un StateFlow */
    val todos by viewModel.todos
        .collectAsStateWithLifecycle()

    PMDMComposeTheme {
        TodoListScreen(
            todos = todos,
            onTodoIsDoneChange = viewModel::onTodoIsDoneChange,
            onTodoDelete = viewModel::onTodoDelete,
            onTodoAdd = viewModel::onTodoAdd
        )
    }

}