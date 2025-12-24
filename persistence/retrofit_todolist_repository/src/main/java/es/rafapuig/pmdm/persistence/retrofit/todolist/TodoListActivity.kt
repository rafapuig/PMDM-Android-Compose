package es.rafapuig.pmdm.persistence.retrofit.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.screens.TodoListScreen
import es.rafapuig.pmdm.persistence.retrofit.todolist.ui.theme.PMDMComposeTheme

class TodoListActivity : ComponentActivity() {

    val viewModel by viewModels<TodoListViewModel>(
        factoryProducer = { TodoListViewModel.Factory }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {

                val todos by viewModel.todos.collectAsStateWithLifecycle()
                val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

                val snackbarHostState = remember { SnackbarHostState() }

                TodoListScreen(
                    todos = todos,
                    isLoading = isLoading,
                    snackbarHostState = snackbarHostState,
                    onTodoIsDoneChange = viewModel::onTodoIsDoneChange,
                    onTodoDelete = viewModel::onTodoDelete,
                    onTodoAdd = viewModel::onTodoAdd,
                    onRefresh = viewModel::refresh
                )
            }
        }
    }
}



