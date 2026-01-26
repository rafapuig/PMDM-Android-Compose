package es.rafapuig.pmdm.clean.authentication.auth.navigation

sealed interface SessionEvent {
    data object LoggedOut : SessionEvent
}
