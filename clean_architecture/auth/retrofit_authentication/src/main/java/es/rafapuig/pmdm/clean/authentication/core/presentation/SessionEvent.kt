package es.rafapuig.pmdm.clean.authentication.core.presentation

sealed interface SessionEvent {
    data object LoggedOut : SessionEvent
}