package es.rafapuig.pmdm.clean.authentication.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState(isLoading = true)

            runCatching {
                registerUseCase(email, password)
            }.onSuccess {
                _uiState.value = RegisterUiState()
            }.onFailure {
                _uiState.value = RegisterUiState(
                    error = it.message ?: "Error al registrar"
                )
            }
        }
    }
}
