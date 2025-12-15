package es.rafapuig.pmdm.compose.viewmodel.counter.no_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.compose.viewmodel.counter.no_viewmodel.screens.CounterScreenRoot
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

class CounterActivity : ComponentActivity() {
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