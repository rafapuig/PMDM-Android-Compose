@file:OptIn(SavedStateHandleSaveableApi::class)

package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/**
 * El ViewModel conserva el estado de la UI en tanto que sobrevive a los cambios de configuración
 * pero una instacia de ViewModel no sobrevive al system-initiated process death
 *
 * Por tanto, si queremos conservar estado de la UI y que persista al system-initiated process death
 * debemos usar el SavedStateHandle y guardar el estado de la instancia de ViewModel
 */
class UiStateViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {


    /**
     * Normalmente no queremos que desde fuera de clase se pueda modificar directamente
     * el valor de las propiedades del estado del ViewModel
     */

    /**
     * Propiedad mutable no persistente (si se destruye el ViewModel -> system-initiated process death)
     */
    var counterNormal = 0
        private set

    /**
     * Propiedad mutable persistente (se recrea a partir del SavedStateHandle)
     */
    var counterSaved = savedStateHandle.get<Int>("counterSaved") ?: 0
        get() = field
        private set(value) {
            field = value
            savedStateHandle["counterSaved"] = field
        }


    /**
     * Propiedad mutable que delega en un MutableState de Compose
     * (no persistente a la destrucción del ViewModel)
     */
    var counterState by mutableStateOf(0)
        private set


    /**
     * Propiedad mutable persistente que delega en un MutableState de Compose
     *
     * Para el caso de la versión persistente no podemos utilizar la delegacion directa
     * usando el operador by, pues en ese caso no nos dejaría implementar el setter personalizado
     * para llamar a savedStateHandle[COUNTER_KEY] = value
     *
     * Por tanto guardamos directamente el MutableState en una propiedad...
     */
    private var counterSavedStateDelegate =
        mutableStateOf(savedStateHandle.get<Int>("counterSavedState") ?: 0)

    /**
     * ...y delegamos manualmente en una propiedad de tipo Int usando el value del MutableState
     * definiendo un getter y un setter
     */
    var counterSavedState
        get() = counterSavedStateDelegate.value
        set(value) {
            counterSavedStateDelegate.value = value
            savedStateHandle["counterSavedState"] = value
        }


    /**
     * Tenemos otra forma para crear una propiedad mutable que delegue en un MutableState de Compose:
     * - función de extensión de un SavedStateHandle saveable
     *
     * La función saveable está disponible mediante el artefacto
     * lifecycle-viewmodel-compose
     *
     * Permite guardar el estado como si fuera rememberSaveable en el Composable
     */
    var counterSavedStateBySaveable by savedStateHandle.saveable { mutableStateOf(0) }
        private set


    /**
     * Propiedad para mantener el estado de la UI mediante StateFlows
     * No seria persistente a la destrucción del ViewModel --> system-initiated process death
     * (solamente a los cambios de configuración, ya que estos no destruyen el ViewModel)
     */
    private val _counterFlow = MutableStateFlow(0)
    val counterFlow: StateFlow<Int> = _counterFlow.asStateFlow() // Versión de solo-lectura del StateFlow



    /**
     * Propiedad para mantener el estado de la UI mediante StateFlows
     * Persistente a la destrucción del ViewModel --> system-initiated process death
     * Gracias a los metodos getStateFlow y getMutableStateFlow
     * Mantiene el valor del contador incluso si el proceso muere
     */

    private val _counterSavedMutableFlow = savedStateHandle.getMutableStateFlow("counterSavedMutableFlow", 0)
    val counterSavedMutableFlow: StateFlow<Int> = _counterSavedMutableFlow.asStateFlow()


    val counterSavedFlow: StateFlow<Int> = savedStateHandle.getStateFlow("counterSavedFlow", 0)

    /**
     * Metodos públicos para establecer el valor de las propiedades del estado del ViewModel
     */

    fun onCounterNormalChange(newValue: Int) {
        /** Se llama al setter de la propiedad */
        counterNormal = newValue
    }

    fun onCounterSavedChange(newValue: Int) {
        /** Se llama al setter de la propiedad */
        counterSaved = newValue
    }


    fun onCounterStateChange(newValue: Int) {
        /** Se llama al setter de la propiedad */
        counterState = newValue
    }

    fun onCounterSavedStateChange(newValue: Int) {
        /** Se llama al setter de la propiedad */
        counterSavedState = newValue
    }

    fun onCounterSavedStateBySaveableChange(newValue: Int) {
        /** Se llama al setter de la propiedad */
        counterSavedStateBySaveable = newValue
    }

    fun onCounterFlowChange(newValue: Int) {
        //_counterFlow.value = newValue  <-- asi no es seguro en multihilo
        _counterFlow.update { newValue } // <-- mejor usar el metodo update
    }

    fun onCounterSavedMutableFlowChange(newValue: Int) {
        _counterSavedMutableFlow.update { newValue }
    }

    fun onCounterSavedFlowChange(newValue: Int) {
        savedStateHandle["counterSavedFlow"] = newValue  // // Se guarda automáticamente en el Flow
    }

}