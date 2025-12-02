@file:OptIn(SavedStateHandleSaveableApi::class)

package es.rafapuig.pmdm.compose.lifecycle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable


/**
 * El ViewModel conserva el estado de la UI en tanto que sobrevive a los cambios de configuración
 * pero no sobrevive al system-initiated process death
 *
 * Por tanto, si queremos conservar estado de la UI que sobreviva al system-initiated process death
 * debemos usar el SavedStateHandle y guardad el estado de la instancia
 */
class UiStateViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    var counterNormalState by mutableStateOf(0)
        private set

    // Mantiene el valor del contador incluso si el proceso muere
    val counterSavedFlow = savedStateHandle.getStateFlow("counter", 0)

    /**
     * La función saveable está disponible mediante el artefacto
     * lifecycle-viewmodel-compose
     *
     * Permite guardar el estado como si fuera rememberSaveable en el Composable
     */
    var counterSavedState by savedStateHandle.saveable{ mutableStateOf(0) }
        private set

    /**
     * Metodos para cambiar el estado de la UI mediante el ViewModel
     */
    fun onCounterSavedFlowChange(newValue: Int) {
        savedStateHandle["counter"] = newValue  // // Se guarda automáticamente
    }

    fun onCounterSavedStateChange(newValue: Int) {
        counterSavedState = newValue
    }

    fun onCounterNormalStateChange(newValue: Int) {
        counterNormalState = newValue
    }

}