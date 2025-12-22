package es.rafapuig.pmdm.di.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.di.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.di.todolist.presentation.screens.TodoListScreenRoot
import es.rafapuig.pmdm.di.todolist.ui.theme.PMDMComposeTheme

class TodoListActivity : ComponentActivity() {

    val viewModel by viewModels<TodoListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                TodoListScreenRoot(viewModel)
            }
        }
    }
}



