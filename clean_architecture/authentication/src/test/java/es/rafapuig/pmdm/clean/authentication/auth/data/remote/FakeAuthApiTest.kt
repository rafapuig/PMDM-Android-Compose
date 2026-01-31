package es.rafapuig.pmdm.clean.authentication.auth.data.remote

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApi.Messages.INVALID_CREDENTIALS
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApi.Messages.INVALID_USER
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApi.Messages.USER_ALREADY_EXISTS
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.LoginRequest
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.dto.RegisterRequest
import es.rafapuig.pmdm.clean.authentication.core.data.remote.dto.ErrorResponse
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FakeAuthApiTest {

    val json = Json { ignoreUnknownKeys = true }
    lateinit var fakeAuthApi: FakeAuthApi

    @Before
    fun setup() {
        fakeAuthApi = FakeAuthApi()
    }

    @Test
    fun `test success login`() = runTest {

        val email = "test@test.com"
        val password = "1234"

        val response =
            fakeAuthApi.login(LoginRequest(email, password))

        assertTrue(response.isSuccessful)
        assertEquals("fake-token-$email", response.body()?.token)
    }

    @Test
    fun `test login with invalid credentials`() = runTest {
        val email = "test@test.com"
        val password = "1111"
        val apiError = ErrorResponse(401, INVALID_CREDENTIALS)

        val response =
            fakeAuthApi.login(LoginRequest(email, password))

        assertFalse(response.isSuccessful)
        response.errorBody()?.let {
            val error = json.decodeFromString(ErrorResponse.serializer(), it.string())
            assertEquals(INVALID_CREDENTIALS, error.message)
            assertEquals(401, error.code)
        }
    }

    @Test
    fun `test login with invalid email`() = runTest {
        val email = "test@test"
        val password = "1234"
        val apiError = ErrorResponse(404, INVALID_USER)

        val response =
            fakeAuthApi.login(LoginRequest(email, password))

        assertFalse(response.isSuccessful)
        response.errorBody()?.let {
            val error = json.decodeFromString(ErrorResponse.serializer(), it.string())
            assertEquals(INVALID_USER, error.message)
            assertEquals(404, error.code)
        }
    }

    @Test
    fun `register success`() = runTest {
        val email = "other@test.com"
        val password = "1234"

        val response =
            fakeAuthApi.register(RegisterRequest(email, password))

        assertTrue(response.isSuccessful)
        assertEquals("fake-token-$email", response.body()?.token)
    }

    @Test
    fun `register with duplicated email`() = runTest {
        val email = "dup@test.com"
        val password = "1234"

        fakeAuthApi.register(RegisterRequest(email, password))

        val response =
            fakeAuthApi.register(RegisterRequest(email, password))

        assertFalse(response.isSuccessful)
        response.errorBody()?.let {
            val error = json.decodeFromString(ErrorResponse.serializer(), it.string())
            assertEquals(USER_ALREADY_EXISTS, error.message)
            assertEquals(409, error.code)
        }
    }

}