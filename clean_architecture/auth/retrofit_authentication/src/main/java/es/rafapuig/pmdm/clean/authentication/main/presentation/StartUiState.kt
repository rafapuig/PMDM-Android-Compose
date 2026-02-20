package es.rafapuig.pmdm.clean.authentication.main.presentation

sealed interface StartUiState {
    data object Loading : StartUiState
    data object Logged : StartUiState
    data object NotLogged : StartUiState
}

