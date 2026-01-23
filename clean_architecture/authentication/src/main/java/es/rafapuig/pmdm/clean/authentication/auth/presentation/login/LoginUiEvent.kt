package es.rafapuig.pmdm.clean.authentication.auth.presentation.login

sealed interface LoginUiEvent {
    object LoginSuccess : LoginUiEvent
}