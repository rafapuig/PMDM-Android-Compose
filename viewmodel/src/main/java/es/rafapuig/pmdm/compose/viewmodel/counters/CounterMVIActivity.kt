package es.rafapuig.pmdm.compose.viewmodel.counters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.CounterCombinedStateFlowMVIViewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.CounterDataStoreMVIViewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.CounterStateFlowMVIViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.screens.CounterMVIScreen
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

class CounterMVIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //val viewModel =
                viewModel<CounterCombinedStateFlowMVIViewModel>()
            //val viewModel = viewModel<CounterStateFlowMVIViewModel>()
            val viewModel = viewModel<CounterDataStoreMVIViewModel>()

            val title = when (viewModel) {
                is CounterCombinedStateFlowMVIViewModel -> "Contador Combined StateFlow MVI"
                is CounterStateFlowMVIViewModel -> "Contador con StateFlow MVI"
                is CounterDataStoreMVIViewModel -> "Contador con DataStore MVI"
                else -> "Contador"
            }

            PMDMComposeTheme {
                CounterMVIScreen(
                    uiState = viewModel.uiState.collectAsState().value,
                    onAction = viewModel::dispatch,
                    title = title
                )
            }
        }
    }
}