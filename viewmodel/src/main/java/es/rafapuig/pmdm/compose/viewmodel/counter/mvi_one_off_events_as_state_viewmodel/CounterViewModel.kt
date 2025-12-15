package es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_state_viewmodel

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel() : ViewModel() {

    /**
     * En MVI declaramos un StateFlow para el estado de la UI
     */
    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState = _uiState.asStateFlow()

    /**
     * Y un dispatcher de las acciones del usuario
     */
    fun onAction(action: CounterAction) {
        when (action) {
            CounterAction.Increment -> increment()
            CounterAction.Decrement -> decrement()
            CounterAction.Reset -> reset()
            is CounterAction.Set -> _uiState.update { it.copy(counter = action.counter) }
            CounterAction.OnMessageShown -> onMessageShown()
        }
    }


    private fun onCounterReset() {
        onMessage("Counter reseteado")
    }

    private fun onMessage(message: String) {
        _uiState.update { it.copy(message = message) }
    }

    fun onMessageShown() {
        _uiState.update { it.copy(message = null) }
    }


    /**
     * El ViewModel proporciona un scope de corrutinas ligado al ciclo de vida del ViewModel
     */
    private fun slowAction(action: () -> Unit) {
        viewModelScope.launch {
            isLoading(true)
            delay(500)
            action()
            isLoading(false)

        }
    }

    /**
     * El resto de métodos se mantienen iguales pero se hacen privados
     */

    /**
     * Para que se actualice el estado como tal tenemos que asignarle
     * otro objeto nuevo con las propiedades que tenia el actual pero
     * con las que queremos que cambien modificadas en el nuevo objeto
     * Para ello es util el metodo copy de los data class de kotlin
     */

    private fun isLoading(value: Boolean) {
        _uiState.update { it.copy(isLoading = value) }
    }

    private fun incrementCounter() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    private fun decrementCounter() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    private fun resetCounter() {
        _uiState.update {
            it.copy(counter = 0).also {
                onCounterReset()
            }
        }
    }


    private fun increment() = slowAction { decrementCounter() }
    private fun decrement() = slowAction { incrementCounter() }
    private fun reset() = slowAction { resetCounter() }

}