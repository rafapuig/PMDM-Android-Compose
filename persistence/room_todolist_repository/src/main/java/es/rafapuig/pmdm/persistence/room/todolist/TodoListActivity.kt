package es.rafapuig.pmdm.persistence.room.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.persistence.room.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.persistence.room.todolist.presentation.screens.TodoListScreen
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme

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

                TodoListScreen(
                    todos = todos,
                    onTodoIsDoneChange = viewModel::onTodoIsDoneChange,
                    onTodoDelete = viewModel::onTodoDelete,
                    onTodoAdd = viewModel::onTodoAdd
                )
            }
        }
    }
}



