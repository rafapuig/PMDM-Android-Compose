package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
