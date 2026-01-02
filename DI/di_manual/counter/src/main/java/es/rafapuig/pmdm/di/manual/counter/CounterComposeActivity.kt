package es.rafapuig.pmdm.di.manual.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.di.manual.counter.data.CounterDataStore
import es.rafapuig.pmdm.di.manual.counter.data.CounterRepositoryImpl
import es.rafapuig.pmdm.di.manual.counter.data.counterDataStore
import es.rafapuig.pmdm.di.manual.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.manual.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.di.manual.counter.ui.theme.PMDMComposeTheme

class CounterComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                val viewModel = provideCounterViewModel()

                MVICounterScreenRoot(viewModel)
            }
        }
    }
}


@Composable
private fun provideCounterViewModel() : CounterViewModel {
    /** Creamos el viewmodel mediante la obtención manual de las dependencias */

    val context = LocalContext.current
    val counterDataStore = CounterDataStore(context.applicationContext.counterDataStore)
    val counterRepository = CounterRepositoryImpl(counterDataStore)

    val viewModel = viewModel { CounterViewModel(counterRepository) }

    return viewModel
}