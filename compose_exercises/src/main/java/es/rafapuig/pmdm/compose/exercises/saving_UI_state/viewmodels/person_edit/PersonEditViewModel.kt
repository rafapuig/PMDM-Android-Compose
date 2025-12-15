package es.rafapuig.pmdm.compose.exercises.saving_UI_state.viewmodels.person_edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class PersonEditViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        private const val UI_STATE_KEY = "PersonEditUiState"
    }

    val initialValue = PersonEditUiState()

    private val _uiState =
        savedStateHandle.getMutableStateFlow(UI_STATE_KEY, initialValue)
    val uiState = _uiState.asStateFlow()

    /*init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(500.milliseconds)
            _uiState.update { it.copy(isLoading = false) }
        }
    }*/

    fun onAction(action: PersonEditAction) {
        when (action) {
            PersonEditAction.OnClearPerson -> onPersonChange(null)
            is PersonEditAction.OnSetPerson -> onPersonChange(action.person)
            is PersonEditAction.OnAgeChanged -> onAgeChange(action.age)
            is PersonEditAction.OnNameChanged -> onNameChange(action.name)
            PersonEditAction.OnLoadPerson -> onLoadPerson()
        }
    }

    private fun onLoadPerson() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(500.milliseconds)
            onPersonChange(
                Person(
                    name = "Rafa Puig",
                    age = 48
                )
            )
            _uiState.update { it.copy(isLoading = false) }
        }

    }

    fun onPersonChange(person: Person?) {
        _uiState.update { it.copy(person = person) }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(person = it.person?.copy(name = name) ?: Person(name, null)) }
    }

    fun onAgeChange(age: Int?) {
        _uiState.update { it.copy(person = it.person?.copy(age = age) ?: Person("", age)) }
    }

}