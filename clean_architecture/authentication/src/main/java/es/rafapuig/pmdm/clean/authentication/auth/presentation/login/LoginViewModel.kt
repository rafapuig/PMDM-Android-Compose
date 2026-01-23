package es.rafapuig.pmdm.clean.authentication.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.LoginUseCase
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

    fun login(email: String, password: String) {
        viewModelScope.launch {

            _uiState.value = LoginUiState(isLoading = true)

            runCatching {
                loginUseCase(email, password)
            }.onSuccess {
                _uiState.value = LoginUiState()
                _eventChannel.send(LoginUiEvent.LoginSuccess)
            }.onFailure {
                _uiState.value = LoginUiState(
                    error = it.message ?: "Error inesperado"
                )
            }
        }
    }
}
