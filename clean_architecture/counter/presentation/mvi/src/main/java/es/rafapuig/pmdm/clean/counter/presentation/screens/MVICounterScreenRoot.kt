package es.rafapuig.pmdm.clean.counter.presentation.screens

import android.content.res.Configuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.clean.counter.presentation.CounterEvent
import es.rafapuig.pmdm.clean.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.clean.counter.presentation.ObserveAsEvents
import es.rafapuig.pmdm.clean.counter.presentation.ui.theme.CleanCounterTheme


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

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MVICounterScreenRootPreview() {
    CleanCounterTheme {
        MVICounterScreenRoot()
    }
}