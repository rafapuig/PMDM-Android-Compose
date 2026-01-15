package es.rafapuig.pmdm.di.users.koin.compose.data.repository

import es.rafapuig.pmdm.di.users.koin.compose.domain.model.User
import es.rafapuig.pmdm.di.users.koin.compose.domain.repository.UserRepository

class UserRepositoryListImpl() : UserRepository {

    val users = mutableListOf<User>()

    override suspend fun findUser(name: String): User? {
        return users.firstOrNull { name.lowercase() in it.name.lowercase() }
    }

    override suspend fun addUsers(users: List<User>) {
        this.users.addAll(users)
    }

}