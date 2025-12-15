package es.rafapuig.pmdm.compose.viewmodel.counter.stateFlows_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel() : ViewModel() {

    /**
     * Usamos StateFlows de Kotlin para los estados de la UI
     * La referencia mutable la hacemos privada para controlar que no se pueda emitir
     * desde fuera del ViewModel
     * La referencia no mutable la obenemos mediante la función asStateFlow()
     * Esta referencia la hacemos publica porque con esta solo se puede leer el estado
     */

    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    /*var counter by mutableIntStateOf(0) // No hay remember, es una propiedad de la clase
        private set // Se controla que no se pueda modificar desde fuera del ViewModel

    var isLoading by mutableStateOf(false)
        private set
     */

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
     * Los incrementos y decrementos no son operaciones atómicas
     * Para evitar race-conditions usamos el método update de StateFlow
     */
    fun increment() = slowAction { _counter.update { it + 1 } }
    fun decrement() = slowAction { _counter.update { it - 1 } }

    fun reset() = slowAction {
        _counter.tryEmit(0) // o tryEmit fuera de una corrutina
    }



}