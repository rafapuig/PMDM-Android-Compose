package es.rafapuig.pmdm.compose.exercises.viewmodels.navigation.exercise2.counter.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {

    private val _counter = MutableStateFlow<Int>(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun onIncrement() {
       _counter.update { it + 1 }
    }

    fun onDecrement() {
        _counter.update { it - 1 }
    }

    fun onReset() {
        _counter.update { 0 }
    }

    init {
        println("CounterViewModel created")
    }

    /**
     * Se llama cuando el ViewModel ya no se usa y se va a destruir.
     * Cualquier referencia al contexto debe ser limpiada aquí para evitar
     * fugas de memoria.
     * https://developer.android.com/topic/libraries/architecture/viewmodel#lifecycle
     */
    override fun onCleared() {
        super.onCleared()
        println("CounterViewModel cleared")
    }

}