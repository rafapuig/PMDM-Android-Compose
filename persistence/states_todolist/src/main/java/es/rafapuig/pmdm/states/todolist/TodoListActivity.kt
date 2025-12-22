package es.rafapuig.pmdm.states.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.states.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.states.todolist.presentation.screens.TodoListScreen
import es.rafapuig.pmdm.states.todolist.ui.theme.PMDMComposeTheme

class TodoListActivity : ComponentActivity() {

    val viewModel by viewModels<TodoListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                TodoListScreen(
                    todos = viewModel.todos,
                    onTodoIsDoneChange = viewModel::onTodoIsDoneChange,
                    onTodoDelete = viewModel::onTodoDelete,
                    onTodoAdd = viewModel::onTodoAdd
                )
            }
        }
    }
}