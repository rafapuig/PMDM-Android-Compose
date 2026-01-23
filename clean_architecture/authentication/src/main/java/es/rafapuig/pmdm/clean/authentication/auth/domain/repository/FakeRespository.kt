package es.rafapuig.pmdm.clean.authentication.auth.domain.repository

import es.rafapuig.pmdm.clean.authentication.auth.domain.model.User
import kotlinx.coroutines.delay

/**
 * ✅ Ideal para tests de ViewModel
 * ❌ No testea networking
 */

class FakeAuthRepository : AuthRepository {

    private var loggedIn = false

    override suspend fun login(email: String, password: String): User {
        delay(300)
        if (email != "test@test.com") throw Exception("Error")
        loggedIn = true
        return User(email = email)
    }

    override suspend fun register(email: String, password: String): User {
        delay(300)
        loggedIn = true
        return User(email = email)
    }

    override suspend fun logout() {
        loggedIn = false
    }

    override suspend fun isUserLoggedIn(): Boolean = loggedIn
}
