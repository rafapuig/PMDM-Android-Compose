package es.rafapuig.pmdm.di.app_container.counter.presentation.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.di.app_container.counter.presentation.CounterEvent
import es.rafapuig.pmdm.di.app_container.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.app_container.counter.presentation.ObserveAsEvents


@Composable
fun MVICounterScreenRoot(
    viewModel: CounterViewModel = viewModel(factory = CounterViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    /**
     * Consumimos los eventos recolectando el flow de eventos del ViewModel
     * (ver el código de la función ObserveAsEvents)
     */
    viewModel.events.ObserveAsEvents { event ->
        when (event) {
            is CounterEvent.Message -> snackbarHostState.showSnackbar(event.message)
            CounterEvent.CounterReset -> snackbarHostState.showSnackbar("Counter reseteado")
        }
    }

    MVICounterScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState
    )
}