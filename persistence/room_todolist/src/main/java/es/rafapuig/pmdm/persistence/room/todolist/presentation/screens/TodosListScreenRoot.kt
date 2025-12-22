package es.rafapuig.pmdm.persistence.room.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.TodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.mappers.toDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.mappers.toDomain
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.TodosDatabaseProvider
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.getDatabaseInstance
import es.rafapuig.pmdm.persistence.room.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.room.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Preview
@Composable
fun TodosListScreenRoot(
    viewModel: TodoListViewModel = viewModel(factory = TodoListViewModel.Factory)
) {
    val todos by viewModel.todos.collectAsStateWithLifecycle()

    TodoListScreen(
        todos = todos,
        onTodoIsDoneChange = viewModel::onTodoIsDoneChange,
        onTodoDelete = viewModel::onTodoDelete,
        onTodoAdd = viewModel::onTodoAdd
    )
}