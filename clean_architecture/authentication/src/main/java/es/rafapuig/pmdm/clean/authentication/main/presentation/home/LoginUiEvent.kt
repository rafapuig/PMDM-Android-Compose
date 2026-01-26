package es.rafapuig.pmdm.clean.authentication.main.presentation.home

sealed interface HomeUiEvent {
    object LoggedOut : HomeUiEvent
}