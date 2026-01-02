package es.rafapuig.pmdm.service_locator.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.service_locator.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.service_locator.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.service_locator.counter.ui.theme.PMDMComposeTheme

class CounterComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                val viewModel = viewModel<CounterViewModel> {
                    CounterViewModel(CounterServiceLocator.counterRepository)
                }
                MVICounterScreenRoot(viewModel)
            }
        }
    }
}