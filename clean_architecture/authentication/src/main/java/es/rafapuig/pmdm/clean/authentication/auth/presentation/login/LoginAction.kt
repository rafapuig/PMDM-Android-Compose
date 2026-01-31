package es.rafapuig.pmdm.clean.authentication.auth.presentation.login

interface LoginAction {
    data class OnLoginClick(val email: String, val password: String) : LoginAction
    object OnCreateAccountClick : LoginAction
}