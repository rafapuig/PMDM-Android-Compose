package es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_channel_flow_viewmodel.screens

import android.content.res.Configuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_channel_flow_viewmodel.CounterEvent
import es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_channel_flow_viewmodel.CounterViewModel
import es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_channel_flow_viewmodel.ObserveAsEvents
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme


@Composable
fun MVICounterScreenRoot(viewModel: CounterViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    /**
     * Consumimos los eventos recolectando el flow de eventos del ViewModel
     * (ver el código de la función ObserveAsEvents)
     */
    viewModel.events.ObserveAsEvents { event ->
        when (event) {
            is CounterEvent.Error -> snackbarHostState.showSnackbar(event.message)
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
    PMDMComposeTheme {

        val viewModel = viewModel<CounterViewModel>()

        MVICounterScreenRoot(viewModel)
    }
}