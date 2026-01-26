package es.rafapuig.pmdm.clean.authentication.auth.domain.usecase

import es.rafapuig.pmdm.clean.authentication.auth.domain.model.User
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LoginUseCaseTest {

    private lateinit var repository: AuthRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        repository = mockk()
        loginUseCase = LoginUseCase(repository)
    }

    @Test
    fun `should return user when credentials are valid`() = runTest {
        // Arrange
        val email = "test@mail.com"
        val password = "123456"
        val token = "fake_token"
        val expectedUser = User(id = "1", email = email, token = token)

        // Stub del metodo login de repository
        coEvery { repository.login(email, password) } returns expectedUser

        // Act
        val result = loginUseCase(email, password)

        // Assert
        assertEquals(expectedUser, result)
        coVerify(exactly = 1) { repository.login(email, password) }
    }

    @Test
    fun `should throw exception when email is empty`() = runTest {
        // Arrange
        val email = ""
        val password = "123456"

        // Act
        assertFailsWith<IllegalArgumentException> {
            loginUseCase(email, password)
        }
    }
}