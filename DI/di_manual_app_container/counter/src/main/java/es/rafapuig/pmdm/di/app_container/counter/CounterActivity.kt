package es.rafapuig.pmdm.di.app_container.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.di.app_container.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.app_container.counter.presentation.CounterViewModelFactory
import es.rafapuig.pmdm.di.app_container.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.di.app_container.counter.ui.theme.PMDMComposeTheme

class CounterActivity : ComponentActivity() {

    val viewModel by viewModels<CounterViewModel>(
        factoryProducer = {
            val appContainer = (application as CounterApplication).container
            CounterViewModelFactory(appContainer.counterRepository)
        }
    )

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