package es.rafapuig.pmdm.compose.viewmodel.counter.ui_state_compose_state_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel() : ViewModel() {

    /**
     * No declaramos ningún estado de Compose independiente para cada elemento
     * del estado de la UI
     */
    /*
    var counter by mutableIntStateOf(0) // No hay remember, es una propiedad de la clase
        private set // Se controla que no se pueda modificar desde fuera del ViewModel

    var isLoading by mutableStateOf(false)
        private set
    */

    // https://developer.android.com/topic/architecture/ui-layer/state-production#mutating_the_ui_state_from_background_threads


    var uiState by mutableStateOf(CounterUiState())
    private set

    /**
     * El ViewModel proporciona un scope de corrutinas ligado al ciclo de vida del ViewModel
     */
    private fun slowAction(action: suspend () -> Unit) {
        viewModelScope.launch {
            isLoading(true)
            delay(500)
            action()
            isLoading(false)

        }
    }

    /**
     * Para que se actualice el estado como tal tenemos que asignarle
     * otro objeto nuevo con las propiedades que tenia el actual pero
     * con las que queremos que cambien modificadas en el nuevo objeto
     * Para ello es util el metodo copy de los data class de kotlin
     */

    private fun isLoading(value: Boolean) {
        Snapshot.withMutableSnapshot {
            uiState = uiState.copy(isLoading = value)
        }
    }

    private fun incrementCounter() {
        Snapshot.withMutableSnapshot {
            uiState = uiState.copy(counter = uiState.counter + 1)
        }
    }

    private fun decrementCounter() {
        Snapshot.withMutableSnapshot {
            uiState = uiState.copy(counter = uiState.counter - 1)
        }
    }

    private fun resetCounter() {
        Snapshot.withMutableSnapshot {
            uiState = uiState.copy(counter = 0)
        }
    }


    fun increment() = slowAction { incrementCounter() }
    fun decrement() = slowAction { decrementCounter() }
    fun reset() = slowAction { resetCounter() }

}