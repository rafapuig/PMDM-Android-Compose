package es.rafapuig.pmdm.clean.authentication.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthError
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthException
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.LoginUseCase
import es.rafapuig.pmdm.clean.authentication.auth.presentation.toUiText
import es.rafapuig.pmdm.clean.authentication.core.presentation.toUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _eventChannel = Channel<LoginUiEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when(action) {
            is LoginAction.OnLoginClick -> login(action.email, action.password)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {

            _uiState.value = LoginUiState(isLoading = true)

            runCatching {
                loginUseCase(email, password)
            }.onSuccess {
                _uiState.value = LoginUiState()
                _eventChannel.send(LoginUiEvent.LoginSuccess)
            }.onFailure {throwable ->

                val error = (throwable as? AuthException)?.error
                    ?: AuthError.Unknown

                val errorMessage = error.toUiText()

                _uiState.value = LoginUiState(
                    error = errorMessage
                )
                /*_eventChannel.send(
                    LoginUiEvent.LoginError(errorMessage)
                )*/
            }
        }
    }
}
