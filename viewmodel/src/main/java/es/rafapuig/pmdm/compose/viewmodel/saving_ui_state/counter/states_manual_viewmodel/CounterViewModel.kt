package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.states_manual_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    /**
     * https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate#savedstate-compose-state
     */

    object Keys {
        const val COUNTER_KEY = "counter"
    }

    /**
     * El state que queramos salvar lo hacemos sin usar la función de extensión
     * saveable() del objeto SavedStateHandle
     *
     * No usamos delegacion de propiedad mediante by
     */
    private var counterDelegate = mutableIntStateOf(
        savedStateHandle.get<Int>(Keys.COUNTER_KEY) ?: 0
    )

    /**
     * Para poder definer getter u setter personalizado
     */
    var counter: Int
        get() = counterDelegate.value
        private set(value) {
            counterDelegate.value = value
            savedStateHandle[Keys.COUNTER_KEY] = value
        }

    /**
     * Este estado nos da igual perderlo si el SO mata la app
     */
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