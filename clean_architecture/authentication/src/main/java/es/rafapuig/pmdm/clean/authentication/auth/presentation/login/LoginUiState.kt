package es.rafapuig.pmdm.clean.authentication.auth.presentation.login

import es.rafapuig.pmdm.clean.authentication.core.presentation.UIText

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: UIText? = null
)