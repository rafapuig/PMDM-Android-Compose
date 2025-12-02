package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert.uiState.PersonUpsertUiStateImpl
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PersonUpsertViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val initialValue = PersonUpsertUiStateImpl()

    private val _uiState = savedStateHandle
        .getMutableStateFlow("uiState", initialValue)
    val uiState = _uiState.asStateFlow()

    fun onAction(action: PersonUpsertAction) {
        when (action) {
            is PersonUpsertAction.OnNameChanged -> onNameChange(action.name)
            is PersonUpsertAction.OnAgeChanged -> onAgeChange(action.age)
            PersonUpsertAction.OnClearPerson -> onClearPerson()
        }
    }

    private fun onClearPerson() {
        _uiState.update { it.copy(name = "", age = null) }
    }


    private fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }


    private fun onAgeChange(age: Int?) {
        _uiState.update { it.copy(age = age) }
    }

}