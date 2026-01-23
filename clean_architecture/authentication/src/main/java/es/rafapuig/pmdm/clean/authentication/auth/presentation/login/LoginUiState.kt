package es.rafapuig.pmdm.clean.authentication.auth.presentation.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)