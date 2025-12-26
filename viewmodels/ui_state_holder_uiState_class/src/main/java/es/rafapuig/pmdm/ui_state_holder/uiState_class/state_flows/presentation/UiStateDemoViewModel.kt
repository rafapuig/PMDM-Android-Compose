package es.rafapuig.pmdm.ui_state_holder.uiState_class.state_flows.presentation

import androidx.lifecycle.ViewModel
import es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UiStateDemoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onUpperCaseChange(value: Boolean) {
        _uiState.update { uiState ->
            uiState.copy(
                upperCase = value,
                //name = uiState.name.let { if (value) it.uppercase() else it.lowercase() }
            )
        }
    }

}