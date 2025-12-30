package es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_channel_flow_viewmodel

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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
            //CounterAction.OnMessageShown -> onMessageShown()
        }
    }

    /**
     * Y para los eventos de un solo uso (no son estado!!!)
     * Un Channel + un Flow
     */
    private val _eventChannel = Channel<CounterEvent>()
    val events = _eventChannel.receiveAsFlow()


    init {
        onMessage("ViewModel inicializado")
    }

    /**
     * Para generar un evento basta con enviarlo en el canal
     */
    private fun onCounterReset() {
        //onMessage("Counter reseteado")
        viewModelScope.launch {
            _eventChannel.send(CounterEvent.CounterReset)
        }
    }


    private fun onMessage(message: String) {
        viewModelScope.launch {
            _eventChannel.send(CounterEvent.Error(message))
        }
        //_uiState.update { it.copy(message = message) }
    }

    /**
     * Ya no necesitamos una función que nos informe de que el evento se ha consumido
     * por la UI
     */
    /*fun onMessageShown() {
        _uiState.update { it.copy(message = null) }
    }*/



    private fun slowAction(action: () -> Unit) {
        viewModelScope.launch {
            isLoading(true)
            delay(500)
            action()
            isLoading(false)

        }
    }

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

    private fun increment() = slowAction { incrementCounter() }
    private fun decrement() = slowAction { decrementCounter() }
    private fun reset() = slowAction { resetCounter() }

}