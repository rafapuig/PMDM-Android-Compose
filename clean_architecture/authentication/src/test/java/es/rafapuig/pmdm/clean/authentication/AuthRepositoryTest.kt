package es.rafapuig.pmdm.clean.authentication

import android.util.Log
import es.rafapuig.pmdm.clean.authentication.auth.data.fake.FakeAuthTokenDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.AuthApi
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.AuthRemoteDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.repository.AuthRepositoryImpl
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository
import es.rafapuig.pmdm.clean.authentication.fake.FakeAuthDispatcher
import io.mockk.every
import io.mockk.mockkStatic
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import mockwebserver3.MockWebServer
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlin.test.assertFailsWith

class AuthRepositoryTest {

    private lateinit var server: MockWebServer
    private lateinit var repository: AuthRepository
    private lateinit var tokenDataSource: FakeAuthTokenDataSource

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.isLoggable(any(), any()) } returns false

        server = MockWebServer().apply {
            dispatcher = FakeAuthDispatcher()
            start()
        }

        val json = Json {
            ignoreUnknownKeys = true
        }

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()

        val api = retrofit.create(AuthApi::class.java)

        val remote = AuthRemoteDataSource(api)

        tokenDataSource = FakeAuthTokenDataSource()
        repository = AuthRepositoryImpl(remote, tokenDataSource)
    }

    @After
    fun tearDown() {
        server.close()
        //server.shutdown()
    }

    @Test
    fun `login success saves token`() = runTest {
        repository.register("test@test.com", "1234")

        repository.login("test@test.com", "1234")

        assertNotNull(tokenDataSource.getToken())
    }

    @Test
    fun `login wrong password throws error`() = runTest {
        repository.register("test@test.com", "1234")

        assertFailsWith<Exception> {
            repository.login("test@test.com", "wrong")
        }
    }

    @Test
    fun `register success saves token`() = runTest {
        repository.register("new@test.com", "1234")

        assertNotNull(tokenDataSource.getToken())
    }

    @Test
    fun `register duplicated user throws error`() = runTest {
        repository.register("dup@test.com", "1234")

        assertFailsWith<Exception> {
            repository.register("dup@test.com", "1234")
        }
    }
}
