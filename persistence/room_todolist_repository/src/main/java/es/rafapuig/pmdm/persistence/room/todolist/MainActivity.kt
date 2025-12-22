package es.rafapuig.pmdm.persistence.room.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.persistence.room.todolist.presentation.screens.TodoListScreenRoot
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                TodoListScreenRoot()
            }
        }
    }
}