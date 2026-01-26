package es.rafapuig.pmdm.clean.ktor.authenticacion.core.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.api.*

class AuthInterceptor(private val sessionManager: SessionManager) {
    fun install(client: HttpClient) {
        client.plugin(HttpSend).intercept { request ->
            sessionManager.getToken()?.let { token ->
                request.headers.append("Authorization", "Bearer $token")
            }
            execute(request)
        }
        client.plugin(HttpReceive).intercept { response ->
            if (response.status.value == 401) sessionManager.notifyLoggedOut()
            response
        }
    }
}
