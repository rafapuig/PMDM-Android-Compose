package es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.presentation


import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase.LoginUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase.LogoutUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase.RefreshTokenUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase.RegisterUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.domain.repository.SessionRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed interface SessionEvent { object LoggedOut : SessionEvent }

class SessionManager(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val sessionRepository: SessionRepository
) {
    private val _events = MutableSharedFlow<SessionEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()
    private val scope = CoroutineScope(Dispatchers.IO)

    fun getToken(): String? = sessionRepository.getToken()

    fun login(username: String, password: String, onResult: (Boolean) -> Unit) =
        scope.launch { runCatching { loginUseCase(username, password) }.onSuccess { onResult(true) }.onFailure { onResult(false) } }

    fun register(username: String, password: String, email: String, onResult: (Boolean) -> Unit) =
        scope.launch { runCatching { registerUseCase(username, password, email) }.onSuccess { onResult(true) }.onFailure { onResult(false) } }

    fun logout() = scope.launch { logoutUseCase(); _events.emit(SessionEvent.LoggedOut) }

    fun notifyLoggedOut() = scope.launch { sessionRepository.clearSession(); _events.emit(SessionEvent.LoggedOut) }

    fun refreshToken(onResult: (Boolean) -> Unit) = scope.launch {
        val success = refreshTokenUseCase()
        if (!success) logout()
        onResult(success)
    }
}
