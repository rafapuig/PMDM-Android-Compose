package es.rafapuig.pmdm.clean.authentication.auth.presentation.register

sealed interface RegisterUiEvent {
    object RegisterSuccess : RegisterUiEvent
}