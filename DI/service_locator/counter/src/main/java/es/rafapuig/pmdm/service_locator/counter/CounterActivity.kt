package es.rafapuig.pmdm.service_locator.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.service_locator.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.service_locator.counter.presentation.CounterViewModelFactory
import es.rafapuig.pmdm.service_locator.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.service_locator.counter.ui.theme.PMDMComposeTheme

class CounterActivity : ComponentActivity() {

    val viewModel : CounterViewModel by viewModels {
        CounterViewModelFactory(CounterServiceLocator.counterRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                MVICounterScreenRoot(viewModel)
            }
        }
    }
}