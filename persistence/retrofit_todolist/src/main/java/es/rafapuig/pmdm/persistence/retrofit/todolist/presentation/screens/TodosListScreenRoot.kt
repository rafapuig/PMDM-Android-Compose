package es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.TodoListViewModel

@Preview
@Composable
fun TodosListScreenRoot(
    viewModel: TodoListViewModel = viewModel(factory = TodoListViewModel.Factory)
) {
    val todos = viewModel.todos //.collectAsStateWithLifecycle()

    TodoListScreen(
        todos = todos,
        //onTodoIsDoneChange = viewModel::onTodoIsDoneChange,
        //onTodoDelete = viewModel::onTodoDelete,
        //onTodoAdd = viewModel::onTodoAdd
    )
}