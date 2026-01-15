package es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.di.users.koin.compose.domain.usecase.GreetUserUseCase
import es.rafapuig.pmdm.di.users.koin.compose.domain.usecase.LoadUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

/**
 * The UserViewModel is tagged with @KoinViewModel annotation
 * to declare the Koin ViewModel definition.
 * This ensures proper lifecycle management and avoids memory leaks
 */

@KoinViewModel
class GreetingUserViewModel(
    private val loadUsers: LoadUsersUseCase,
    private val greetUser: GreetUserUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(GreetingUserUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Cargar usuarios en el inicio
        viewModelScope.launch {
            loadUsers()
        }
    }

    fun onAction(action: GreetingUserAction) {
        when (action) {
            is GreetingUserAction.OnNameChange -> onNameChange(action.inputName)
            GreetingUserAction.OnGreet -> onGreet()
        }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name, greetingMessage = "") }
    }

    fun onGreet() {
        viewModelScope.launch {
            val greetingMessage = greetUser(uiState.value.name)
            _uiState.update { it.copy(greetingMessage = greetingMessage) }
        }
    }

}