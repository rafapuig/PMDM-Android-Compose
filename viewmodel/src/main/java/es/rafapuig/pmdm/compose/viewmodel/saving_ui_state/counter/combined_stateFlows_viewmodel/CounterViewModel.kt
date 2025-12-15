package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.combined_stateFlows_viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.combined_stateFlows_viewmodel.CounterViewModel.Keys.COUNTER_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    object Keys {
        const val COUNTER_KEY = "counter"
    }

    private val _counter =
        savedStateHandle.getMutableStateFlow(COUNTER_KEY, 0)
    //val counter = _counter.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    //val isLoading = _isLoading.asStateFlow()

    /**
     * Combinamos estados en un único StateFlow para representar la UI
     */
    val uiState = combine(
        _isLoading,
        _counter
    ) { isLoading, counter ->
        CounterUiState(counter, isLoading)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CounterUiState(_counter.value, _isLoading.value)
    )



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
     * se guardarán en el SavedStateHandle automáticamente
     */
    fun increment() = slowAction { _counter.update { it + 1 } }
    fun decrement() = slowAction { _counter.update { it - 1 } }

    fun reset() = slowAction {
        _counter.tryEmit(0) // o tryEmit fuera de una corrutina
    }

}