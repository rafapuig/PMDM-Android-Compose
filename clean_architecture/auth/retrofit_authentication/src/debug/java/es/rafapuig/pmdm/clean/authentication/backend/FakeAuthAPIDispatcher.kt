package es.rafapuig.pmdm.clean.authentication.backend

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginResponse
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterRequest
import kotlinx.serialization.json.Json
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.RecordedRequest


class FakeAuthAPIDispatcher : Dispatcher() {

    private val users = mutableMapOf<String, String>(
        "test@test.com" to "1234"
    )

    private fun RecordedRequest.requireBody(): String {
        return body?.utf8()
            ?: throw IllegalArgumentException("Request body is missing")
    }

    private fun RecordedRequest.safeBody(): String? =
        body?.utf8()

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.url.encodedPath) {

            "/login" -> handleLogin(request)
            "/register" -> handleRegister(request)

            else -> notFound()
        }
    }

    private fun handleLogin(request: RecordedRequest): MockResponse {
        val body = request.safeBody()
            ?: return badRequest("Body requerido")

        val login = Json.decodeFromString<LoginRequest>(body)

        val password = users[login.email]
            ?: return error(404,"Usuario no existe")

        if (password != login.password) {
            return error(401,"Credenciales inválidas")
        }

        return success(login.email)
    }

    private fun handleRegister(request: RecordedRequest): MockResponse {
        val body = request.safeBody()
            ?: return badRequest("Body requerido")

        val register = Json.decodeFromString<RegisterRequest>(body)

        if (users.containsKey(register.email)) {
            return error(409,"Usuario ya existe")
        }

        users[register.email] = register.password
        return success(register.email)
    }

    private fun success(email: String) =
        MockResponse(
            code = 200,
            body =
                Json.encodeToString(
                    LoginResponse(token = "fake-token-$email")
                )
            )

    private fun notFound() =
        MockResponse(
            code = 404,
            body = """{ "message": "Not found" }"""
        )

    private fun error(code : Int, message: String) =
        MockResponse(
            code = code,
            body = """{ "code": "$code", "message": "$message" }"""
        )

    private fun badRequest(message: String) =
        MockResponse(
            code = 400,
            body = """{ "message": "$message" }"""
        )
}