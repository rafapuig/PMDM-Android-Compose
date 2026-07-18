package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.presentation.SessionManager
import kotlinx.coroutines.flow.update


class LoginViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {

    // Estado de la UI para la pantalla de Login
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun process(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.SubmitLogin -> login(intent.username, intent.password)
        }
    }

    private fun login(username: String, password: String) =
        viewModelScope.launch {
            // Cambiamos el estado de la UI para indicar que se está cargando info
            // y limpiamos los errores para iniciar
            //_state.value = _state.value.copy(isLoading = true, error = null)
            // Es más correcto hacerlo mediante update
            _state.update { it.copy(isLoading = true, error = null) }

            // Intentamos la operación de login con las credenciales proporcionadas
            sessionManager.login(username, password) { success ->
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        success = success,
                        error = if (!success) "Login failed" else null
                    )
                }
            }
        }

}
