package es.rafapuig.pmdm.clean.authentication.auth.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class SessionManager {
    private val _events = Channel<SessionEvent>()
    val events = _events.receiveAsFlow()

    suspend fun notifyLoggedOut() {
        _events.send(SessionEvent.LoggedOut)
    }
}
