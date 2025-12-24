package es.rafapuig.pmdm.persistence.retrofit.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.screens.TodosListScreenCompose

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodosListScreenCompose()
        }
    }
}