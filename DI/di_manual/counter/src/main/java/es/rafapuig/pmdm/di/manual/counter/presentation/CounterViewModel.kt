package es.rafapuig.pmdm.di.manual.counter.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.di.manual.counter.data.CounterDataStore
import es.rafapuig.pmdm.di.manual.counter.data.CounterRepositoryImpl
import es.rafapuig.pmdm.di.manual.counter.data.counterDataStore
import es.rafapuig.pmdm.di.manual.counter.domain.repositories.CounterRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel(
    private val repository: CounterRepository
) : ViewModel() {

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
        }
    }

    /**
     * Y para los eventos de un solo uso (no son estado!!!)
     * Un Channel + un Flow
     */
    private val _eventChannel = Channel<CounterEvent>()
    val events = _eventChannel.receiveAsFlow()


    init {
        observeCounter()
        onMessage("ViewModel inicializado")
    }

    private fun observeCounter() {
        viewModelScope.launch {
            repository.counterFlow.collect { counter ->
                _uiState.update { it.copy(counter = counter) }
            }
        }
    }

    /**
     * Para generar un evento basta con enviarlo en el canal
     */
    private fun onCounterReset() {
        viewModelScope.launch {
            _eventChannel.send(CounterEvent.CounterReset)
        }
    }


    private fun onMessage(message: String) {
        viewModelScope.launch {
            _eventChannel.send(CounterEvent.Message(message))
        }
    }


    private fun slowAction(action: suspend () -> Unit) {
        viewModelScope.launch {
            isLoading(true)
            delay(500)
            try {
                action()
            } catch (e: Exception) {
                onMessage("Error: ${e.message}")
            } finally {
                isLoading(false)
            }
        }
    }

    private fun isLoading(value: Boolean) {
        _uiState.update { it.copy(isLoading = value) }
    }

    private suspend fun incrementCounter() {
        repository.increment()
    }

    private suspend fun decrementCounter() {
        repository.decrement()
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


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as Application

                val counterDataStore = CounterDataStore(application.counterDataStore)
                val repository = CounterRepositoryImpl(counterDataStore)
                CounterViewModel(repository)
            }
        }
    }


}