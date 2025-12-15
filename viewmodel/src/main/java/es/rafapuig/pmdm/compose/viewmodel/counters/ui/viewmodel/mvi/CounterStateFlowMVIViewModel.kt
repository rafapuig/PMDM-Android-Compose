package es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class CounterStateFlowMVIViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(CounterUiState())

    val uiState = _uiState.asStateFlow()

    fun dispatch(action: Action) {
        when (action) {
            Action.Increment -> viewModelScope.launch { slowIncrement() }
            Action.Decrement -> viewModelScope.launch { slowDecrement() }
            Action.Reset -> reset()
        }
    }

    private fun isLoading(state: Boolean) {
        _uiState.update { it.copy(isLoading = state) }
    }

    suspend fun slowIncrement() {
        isLoading(true)
        delay(500.milliseconds)
        increment()
        isLoading(false)
    }

    suspend fun slowDecrement() {
        isLoading(true)
        delay(500.milliseconds)
        decrement()
        isLoading(false)
    }

    private fun increment() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    private fun decrement() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    private fun reset() {
        _uiState.update { it.copy(counter = 0) }
    }
}
