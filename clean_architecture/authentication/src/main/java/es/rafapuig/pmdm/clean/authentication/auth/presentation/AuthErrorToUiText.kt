package es.rafapuig.pmdm.clean.authentication.auth.presentation

import es.rafapuig.pmdm.clean.authentication.R
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthError
import es.rafapuig.pmdm.clean.authentication.core.presentation.UIText

fun AuthError.toUiText() : UIText {

    val stringResource = when (this) {
        AuthError.EmptyCredentials -> R.string.empty_credentials
        AuthError.InvalidCredentials -> R.string.invalid_credentials
        AuthError.InvalidToken -> R.string.invalid_token
        AuthError.Network -> R.string.network_error
        AuthError.Unknown -> R.string.unknown_error
        AuthError.UserAlreadyExists -> R.string.user_already_exists
        AuthError.UserNotFound -> R.string.user_not_found
    }

    return UIText.StringResource(stringResource)
}