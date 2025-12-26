package es.rafapuig.pmdm.clean.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.clean.todolist.presentation.screens.TodosListScreenRoot
import es.rafapuig.pmdm.clean.todolist.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                TodosListScreenRoot()
            }
        }
    }
}