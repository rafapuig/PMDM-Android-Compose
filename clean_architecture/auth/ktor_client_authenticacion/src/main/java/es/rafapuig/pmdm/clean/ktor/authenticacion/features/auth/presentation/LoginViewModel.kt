package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation

sealed interface LoginIntent {
    data class SubmitLogin(val username: String, val password: String) : LoginIntent
}

data class LoginState(val isLoading: Boolean = false, val error: String? = null, val success: Boolean = false)

class LoginViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun process(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.SubmitLogin -> login(intent.username, intent.password)
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            sessionManager.login(username, password) { success ->
                _state.value = if (success) _state.value.copy(isLoading = false, success = true)
                else _state.value.copy(isLoading = false, error = "Login failed")
            }
        }
    }
}
