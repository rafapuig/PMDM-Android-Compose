package es.rafapuig.pmdm.ui_state_holder.state_flows.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class NotePadViewModel : ViewModel() {

    /**
     * Mantiene el estado de la UI mediante un StateFlow
     *
     * Para controlar los cambios de estado la referencia mutable se hace privada
     * asi solamente se puede cambiar el estado desde dentro del ViewModel
     * y desde fuera solo se puede leer el estado con versión de solo lectura
     * obtenida mendiate la función asStateFlow()
     */
    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()


    /**
     * Actualiza el estado de la UI
     * Para ello usamos el metodo update de MutableStateFlow
     * Es la mejor forma ya que la lambda se ejecuta de manera atomica
     * y es segura para multihilo
     */
    fun onTextChange(newText: String) {
        /** Modificamos el estado del stateFlow mediate la función update */
        _text.update { newText }
        // En este caso particular también podríamos haber usado el método value
        // _text.value = newText
    }
}
