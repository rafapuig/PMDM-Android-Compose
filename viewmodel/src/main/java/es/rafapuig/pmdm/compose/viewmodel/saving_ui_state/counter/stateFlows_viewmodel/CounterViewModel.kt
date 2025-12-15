package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.stateFlows_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.stateFlows_viewmodel.CounterViewModel.Keys.COUNTER_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    /**
     * https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate#savedstate-compose-state
     */

    /**
     * Para el caso de un StateFlow tenemos la función
     * getMutableStateFlow() del objeto SavedStateHandle
     */

    object Keys {
        const val COUNTER_KEY = "counter"
    }

    private val _counter =
        savedStateHandle.getMutableStateFlow(COUNTER_KEY, 0)
    val counter = _counter.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    /**
     * El ViewModel proporciona un scope de corrutinas ligado al ciclo de vida del ViewModel
     */
    private fun slowAction(action: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true // Se modifica el estado mediante el mutableStateFlow
            delay(500)
            action()
            _isLoading.emit(false) // Tambien podemos emitirlo (dentro una corrutina)
        }
    }

    /**
     * Todos los cambios que hagamos en el estado del counter
     * se guardarán en el SavedStateHandle automaticamente
     */
    fun increment() = slowAction { _counter.update { it + 1 } }
    fun decrement() = slowAction { _counter.update { it - 1 } }

    fun reset() = slowAction {
        _counter.tryEmit(0) // o tryEmit fuera de una corrutina
    }

}