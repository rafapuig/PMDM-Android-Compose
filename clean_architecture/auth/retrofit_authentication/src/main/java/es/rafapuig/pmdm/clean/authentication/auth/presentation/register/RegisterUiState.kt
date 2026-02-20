package es.rafapuig.pmdm.clean.authentication.auth.presentation.register

import es.rafapuig.pmdm.clean.authentication.core.presentation.UIText

data class RegisterUiState(
    val isLoading: Boolean = false,
    val error: UIText? = null
)
