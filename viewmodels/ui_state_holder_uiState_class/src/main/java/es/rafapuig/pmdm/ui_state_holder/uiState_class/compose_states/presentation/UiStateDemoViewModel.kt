package es.rafapuig.pmdm.ui_state_holder.uiState_class.compose_states.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation.UiState

class UiStateDemoViewModel : ViewModel() {

    var uiState by mutableStateOf(UiState())
    private set

    fun onNameChange(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun onUpperCaseChange(value:Boolean) {
        uiState = uiState.copy(
            upperCase = value,
            //name = uiState.name.let { if (value) it.uppercase() else it.lowercase() }
        )
    }

}