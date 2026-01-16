package es.rafapuig.pmdm.di.users.koin.compose.di

import es.rafapuig.pmdm.di.users.koin.compose.data.repository.UserRepositoryListImpl
import es.rafapuig.pmdm.di.users.koin.compose.domain.repository.UserRepository
import es.rafapuig.pmdm.di.users.koin.compose.domain.usecase.GreetUserUseCase
import es.rafapuig.pmdm.di.users.koin.compose.domain.usecase.LoadUsersUseCase
import es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting.GreetingUserViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single<UserRepository> { UserRepositoryListImpl() }
    // Alternativamente
    //singleOf(::UserRepositoryListImpl) { bind<UserRepository>() }

    //single { GreetUserUseCase(get()) }

    // Aternativamente
    singleOf(::GreetUserUseCase)

    //single { LoadUsersUseCase(get()) }
    // Alternativamente
    singleOf(::LoadUsersUseCase)

    viewModelOf(::GreetingUserViewModel)
    // Alternativamente
    //viewModel { GreetingUserViewModel(get(), get()) }

}