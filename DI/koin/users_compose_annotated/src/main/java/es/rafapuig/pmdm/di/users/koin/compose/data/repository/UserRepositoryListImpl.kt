package es.rafapuig.pmdm.di.users.koin.compose.data.repository

import es.rafapuig.pmdm.di.users.koin.compose.domain.model.User
import es.rafapuig.pmdm.di.users.koin.compose.domain.repository.UserRepository
import org.koin.core.annotation.Single

/**
 * The @Single annotation declares classes as singletons in Koin
 */

@Single
class UserRepositoryListImpl() : UserRepository {

    private val users = mutableListOf<User>()

    override suspend fun findUser(name: String) =
        users.firstOrNull { user ->
            user.name.contains(name, ignoreCase = true)
        }


    override suspend fun addUsers(users: List<User>) {
        this.users.addAll(users)
    }

}