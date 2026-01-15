package es.rafapuig.pmdm.di.users.koin.compose.domain.repository

import es.rafapuig.pmdm.di.users.koin.compose.domain.model.User

interface UserRepository {
    suspend fun findUser(name: String): User?
    suspend fun addUsers(users: List<User>)
}