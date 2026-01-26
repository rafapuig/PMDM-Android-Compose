package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface RegisterIntent {
    data class SubmitRegister(val username: String, val password: String, val email: String) : RegisterIntent
}

data class RegisterState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class RegisterViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun process(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.SubmitRegister -> register(intent.username, intent.password, intent.email)
        }
    }

    private fun register(username: String, password: String, email: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            sessionManager.register(username, password, email) { success ->
                _state.value = if (success)
                    _state.value.copy(isLoading = false, success = true)
                else
                    _state.value.copy(isLoading = false, error = "Registration failed")
            }
        }
    }
}
