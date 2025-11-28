package es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.viewmodel.counters.data.CounterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds


class CounterDataStoreMVIViewModel(application: Application) : AndroidViewModel(application) {

    val repository = CounterRepository(application.applicationContext)

    private val _uiState =
        MutableStateFlow(CounterUiState())

    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            repository.counterFlow.collect {counter ->
                _uiState.update { it.copy(counter = counter) }
            }
        }
    }

    fun dispatch(action: Action) {
        viewModelScope.launch {
            isLoading(true)
            delay(100.milliseconds)
            when (action) {
                Action.Increment -> increment()
                Action.Decrement -> decrement()
                Action.Reset -> reset()
            }
            isLoading(false)
        }
    }

    private fun isLoading(state: Boolean) {
        _uiState.update { it.copy(isLoading = state) }
    }

    private suspend fun increment() {
        repository.increment()
    }

    private suspend fun decrement() {
        repository.decrement()
    }

    private suspend fun reset() {
        repository.reset()
    }
}
