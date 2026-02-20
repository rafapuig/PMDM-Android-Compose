package es.rafapuig.pmdm.clean.authentication.auth.domain

sealed interface AuthError {

    object EmptyCredentials : AuthError
    object InvalidCredentials : AuthError
    object UserNotFound : AuthError
    object UserAlreadyExists : AuthError
    object InvalidToken : AuthError
    object Network : AuthError
    object Unknown : AuthError

}

class AuthException(val error: AuthError) : RuntimeException()