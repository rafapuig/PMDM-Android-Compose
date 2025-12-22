package es.rafapuig.pmdm.persistence.room.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.room.todolist.data.local.entities.TodoEntity
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.getDatabaseInstance
import es.rafapuig.pmdm.persistence.room.todolist.presentation.TodoListViewModel
import es.rafapuig.pmdm.persistence.room.todolist.presentation.screens.TodoListScreen
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme
import kotlin.random.Random

class CustomDatabaseFactoryViewModelActivity : ComponentActivity() {

    val factory = InMemoryTodosDatabaseFactory {
        todoDao().upsertAll(
            List(8) {
                TodoEntity(
                    id = it + 1,
                    task = "Tarea ${it + 1}",
                    isDone = Random.nextBoolean()
                )
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                val context = LocalContext.current

                val db = context.applicationContext
                    .getDatabaseInstance(factory)

                val viewModel =
                    viewModel<TodoListViewModel> {
                        TodoListViewModel(db.todoDao())
                    }

                val todos by viewModel.todos
                    .collectAsStateWithLifecycle()

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