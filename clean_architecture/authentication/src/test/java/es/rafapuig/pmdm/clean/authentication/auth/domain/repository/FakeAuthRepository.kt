package es.rafapuig.pmdm.clean.authentication.auth.domain.repository

import es.rafapuig.pmdm.clean.authentication.auth.domain.model.User

class FakeAuthRepository : AuthRepository  {

    override suspend fun login(
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun isUserLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }
}