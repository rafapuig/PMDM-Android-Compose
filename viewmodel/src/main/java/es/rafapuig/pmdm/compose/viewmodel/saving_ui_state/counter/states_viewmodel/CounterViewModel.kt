package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.states_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    /**
     * https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate#savedstate-compose-state
     */

    /**
     * El state que queramos salvar lo hacemos a traves de la funcion de extensión
     * saveable() del objeto SavedStateHandle
     * esta función se encuentra el la librería
     * "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
     * la misma que define la función que usamos para obtener el viewmodel en Compose
     */
    @OptIn(SavedStateHandleSaveableApi::class)
    var counter by savedStateHandle.saveable { mutableIntStateOf(0) }
        private set

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