package es.rafapuig.pmdm.compose.viewmodel.counter.states_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel() : ViewModel() {

    var counter by mutableIntStateOf(0) // No hay remember, es una propiedad de la clase
        private set // Se controla que no se pueda modificar desde fuera del ViewModel

    var isLoading by mutableStateOf(false)
        private set

    /**
     * El ViewModel proporciona un scope de corrutinas ligado al ciclo de vida del ViewModel
     */
    private fun slowAction(action: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            delay(500)
            action()
            isLoading = false
        }
    }

    fun increment() = slowAction { counter++ }
    fun decrement() = slowAction { counter-- }
    fun reset() = slowAction { counter = 0 }

}