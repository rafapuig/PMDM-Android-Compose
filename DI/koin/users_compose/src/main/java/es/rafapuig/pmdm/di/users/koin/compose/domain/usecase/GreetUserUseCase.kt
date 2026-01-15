package es.rafapuig.pmdm.di.users.koin.compose.domain.usecase

import es.rafapuig.pmdm.di.users.koin.compose.domain.model.User
import es.rafapuig.pmdm.di.users.koin.compose.domain.repository.UserRepository

class GreetUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(name: String): String {
        val user = getUserOrNull(name.trim())
        return prepareHelloMessage(user)
    }

    suspend fun getUserOrNull(name: String): User? = repository.findUser(name)

    fun prepareHelloMessage(user: User?): String {
        return user?.let {
            "Hola ${user.name}, tu email es ${user.email}! 👋"
        } ?: "❌ User not found"
    }

}