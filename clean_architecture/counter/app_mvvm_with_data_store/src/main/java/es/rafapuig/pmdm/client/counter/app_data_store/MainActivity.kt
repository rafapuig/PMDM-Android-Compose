package es.rafapuig.pmdm.client.counter.app_data_store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.clean.counter.presentation.screens.CounterScreenRoot
import es.rafapuig.pmdm.client.counter.app_data_store.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                CounterScreenRoot()
            }
        }
    }
}