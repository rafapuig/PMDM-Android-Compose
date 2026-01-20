package es.rafapuig.pmdm.persistence.room.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.persistence.room.todolist.data.local.entities.TodoEntity
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.presentation.screens.TodosListScreenCompose
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme
import kotlin.random.Random

class CustomDatabaseFactoryComposeActivity : ComponentActivity() {

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
                TodosListScreenCompose(factory)
            }
        }
    }
}

