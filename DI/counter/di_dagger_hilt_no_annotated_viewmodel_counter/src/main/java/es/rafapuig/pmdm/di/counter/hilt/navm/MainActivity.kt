package es.rafapuig.pmdm.di.counter.hilt.navm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import es.rafapuig.pmdm.di.counter.hilt.navm.presentation.CounterScreenRoot
import es.rafapuig.pmdm.di.counter.hilt.navm.presentation.CounterViewModel
import es.rafapuig.pmdm.di.counter.hilt.navm.presentation.CounterViewModelFactory
import es.rafapuig.pmdm.di.counter.hilt.navm.ui.theme.PMDMComposeTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: CounterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CounterViewModel = viewModel(factory = factory)

            PMDMComposeTheme {
                CounterScreenRoot(viewModel)
            }
        }
    }
}