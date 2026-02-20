package es.rafapuig.pmdm.clean.authentication.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthError
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthException
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.RegisterUseCase
import es.rafapuig.pmdm.clean.authentication.auth.presentation.toUiText
import es.rafapuig.pmdm.clean.authentication.core.presentation.toUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()


    private val _eventChannel = Channel<RegisterUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState(isLoading = true)

            runCatching {
                registerUseCase(email, password)
            }.onSuccess {
                _uiState.value = RegisterUiState()
                _eventChannel.send(RegisterUiEvent.RegisterSuccess)
            }.onFailure { throwable ->

                val error = (throwable as? AuthException)?.error
                    ?: AuthError.Unknown

                val message = error.toUiText()

                _uiState.value = RegisterUiState(
                    error = message
                )
            }
        }
    }
}
