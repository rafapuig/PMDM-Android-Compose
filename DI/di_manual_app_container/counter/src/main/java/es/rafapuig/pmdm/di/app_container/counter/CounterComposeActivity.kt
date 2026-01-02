package es.rafapuig.pmdm.di.app_container.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.di.app_container.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.app_container.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.di.app_container.counter.ui.theme.PMDMComposeTheme

class CounterComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context  = LocalContext.current

            val appContainer =
                (context.applicationContext as CounterApplication).container

            val viewModel =
                viewModel { CounterViewModel(appContainer.counterRepository) }

            PMDMComposeTheme {
                MVICounterScreenRoot(viewModel)
            }
        }
    }
}