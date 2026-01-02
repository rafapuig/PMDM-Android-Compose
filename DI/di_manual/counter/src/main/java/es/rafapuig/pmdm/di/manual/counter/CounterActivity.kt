package es.rafapuig.pmdm.di.manual.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.di.manual.counter.data.CounterDataStore
import es.rafapuig.pmdm.di.manual.counter.data.CounterRepositoryImpl
import es.rafapuig.pmdm.di.manual.counter.data.counterDataStore
import es.rafapuig.pmdm.di.manual.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.manual.counter.presentation.CounterViewModelFactory
import es.rafapuig.pmdm.di.manual.counter.presentation.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.di.manual.counter.ui.theme.PMDMComposeTheme

class CounterActivity : ComponentActivity() {

    /** Obtenemos el viewmodel mediante la generación manual de las dependencias */
    val viewModel : CounterViewModel by viewModels {

        val context = applicationContext

        val counterDataStore = CounterDataStore(context.applicationContext.counterDataStore)
        val counterRepository = CounterRepositoryImpl(counterDataStore)

        CounterViewModelFactory(counterRepository)
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