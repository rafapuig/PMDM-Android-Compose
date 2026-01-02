package es.rafapuig.pmdm.di.manual.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import es.rafapuig.pmdm.di.manual.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.di.manual.counter.ui.theme.PMDMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                MVICounterScreenRoot() // Se usa el viewModel del valor por defecto del parámetro
            }
        }
    }
}