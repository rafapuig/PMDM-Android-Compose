package es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds


class CounterCombinedStateFlowMVIViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    val uiState = combine(
        _isLoading,
        _counter
    ) { isLoading, counter ->
        CounterUiState(isLoading, counter)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = CounterUiState()
    )

    private fun performAction(action: Action) {
        when (action) {
            Action.Increment -> increment()
            Action.Decrement -> decrement()
            Action.Reset -> reset()
        }
    }

    fun dispatch(action: Action) {
        viewModelScope.launch {
            isLoading(true)
            delay(300.milliseconds)
            performAction(action)
            isLoading(false)
        }
    }

    private fun isLoading(state: Boolean) {
        _isLoading.update { state }
    }

    private fun increment() {
        _counter.update { it + 1 }
    }

    private fun decrement() {
        _counter.update { it - 1 }
    }

    private fun reset() {
        _counter.update { 0 }
    }
}
