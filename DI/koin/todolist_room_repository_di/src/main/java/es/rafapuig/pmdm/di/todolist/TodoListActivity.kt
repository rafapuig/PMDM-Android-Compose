package es.rafapuig.pmdm.di.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.di.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.di.todolist.presentation.screens.TodoListScreenRoot
import es.rafapuig.pmdm.di.todolist.ui.theme.PMDMComposeTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoListActivity : ComponentActivity() {

    /**
     * In Activity, Fragment or Service, use:
     *
     *     by viewModel() - lazy delegate property
     *     getViewModel() - eager fetch
     *
     * https://insert-koin.io/docs/reference/koin-android/viewmodel/#injecting-viewmodels
     */
    private val viewModel : TodoListViewModel by viewModel() //viewModels<TodoListViewModel>()

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



