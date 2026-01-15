package es.rafapuig.pmdm.di.manual.counter.presentation.screens

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.di.manual.counter.presentation.CounterEvent
import es.rafapuig.pmdm.di.manual.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.di.manual.counter.presentation.ObserveAsEvents

/**
 * El parámetro viewModel tiene definido un valor por defecto,
 * es decir, el valor que se usará si en la llamada a la función MVICounterScreenRoot
 * no se proporciona ningún argumento para dicho parámetro.
 * En ese caso se usa la función viewModel() del ViewModel Scoping API.
 * Esta versión de la función viewModel() recibe una factoría de ViewModels
 * (es decir, una instacia de un objeto cuya clase implementa la interface ViewModelProvider.Factory)
 * Dentro de la clase CounterViewModel, en el companion object, se ha definido una propiedad Factory
 * que devuelve una instancia de un objeto que es una factoría de ViewModels.
 * Dicha factoría fabrica objetos de tipo CounterViewModel.
 */
@Composable
fun MVICounterScreenRoot(
    viewModel: CounterViewModel = viewModel(factory = CounterViewModel.Factory)
) {
    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

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