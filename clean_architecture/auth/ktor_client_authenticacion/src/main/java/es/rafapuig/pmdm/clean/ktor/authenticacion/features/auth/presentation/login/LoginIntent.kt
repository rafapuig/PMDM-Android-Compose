package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation.login

sealed interface LoginIntent {
    data class SubmitLogin(val username: String, val password: String) : LoginIntent
}