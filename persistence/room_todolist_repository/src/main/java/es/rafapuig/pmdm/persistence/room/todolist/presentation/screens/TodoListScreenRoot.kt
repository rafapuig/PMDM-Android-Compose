package es.rafapuig.pmdm.persistence.room.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.room.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun TodoListScreenRoot(
    viewModel: TodoListViewModel = viewModel(factory = TodoListViewModel.Factory)
) {

    /** No necesita valor inicial porque todos es un StateFlow */
    val todos by viewModel.todos.collectAsStateWithLifecycle()

    PMDMComposeTheme {
        TodoListScreen(
            todos = todos,
            onTodoIsDoneChange = viewModel::onTodoIsDoneChange,
            onTodoDelete = viewModel::onTodoDelete,
            onTodoAdd = viewModel::onTodoAdd
        )
    }

}