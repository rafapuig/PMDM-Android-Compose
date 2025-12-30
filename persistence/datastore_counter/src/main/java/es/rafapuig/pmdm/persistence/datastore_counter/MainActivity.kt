package es.rafapuig.pmdm.persistence.datastore_counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.persistence.datastore_counter.presentation.screens.CounterScreenMVVMRoot
import es.rafapuig.pmdm.persistence.datastore_counter.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                CounterScreenMVVMRoot()
            }
        }
    }
}