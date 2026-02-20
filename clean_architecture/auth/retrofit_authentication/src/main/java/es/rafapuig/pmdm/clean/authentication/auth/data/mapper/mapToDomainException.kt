package es.rafapuig.pmdm.clean.authentication.auth.data.mapper

import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthError
import es.rafapuig.pmdm.clean.authentication.auth.domain.AuthException
import java.io.IOException

fun Throwable.mapToDomainException() : AuthException = when(this) {
    is AuthException -> this
    is IOException -> AuthException(AuthError.Network)
    else -> AuthException(AuthError.Unknown)
}
