package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.ui_state_viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.ui_state_viewmodel.CounterViewModel.Keys.UI_STATE_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    /**
     * No declaramos ningún mutableStateFlow independiente para cada elemento
     * del estado de la UI
     */
    /*private val _counter = MutableStateFlow(0)
    //val counter = _counter.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    //val isLoading = _isLoading.asStateFlow()
     */

    object Keys {
        const val UI_STATE_KEY = "ui_state"
    }

    /**
     * Cuando la actividad para a segundo plano -> onStop()
     * se llama a continuación internamente a onSaveInstanceState()
     * y ahi crasheará la app si sabe como guardar un objeto de tipo CounterUiState
     * Por eso, necesitamos que implemente la interface Parcelable
     */
    private val _uiState =
        savedStateHandle.getMutableStateFlow(UI_STATE_KEY, CounterUiState())
    val uiState = _uiState.asStateFlow()

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
        _uiState.update { it.copy(counter = 0) }
    }


    fun increment() = slowAction { incrementCounter() }
    fun decrement() = slowAction { decrementCounter() }
    fun reset() = slowAction { resetCounter() }

}