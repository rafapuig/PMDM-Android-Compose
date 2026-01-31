package es.rafapuig.pmdm.di.users.koin.compose.di

import es.rafapuig.pmdm.di.users.koin.compose.data.repository.UserRepositoryListImpl
import es.rafapuig.pmdm.di.users.koin.compose.domain.repository.UserRepository
import es.rafapuig.pmdm.di.users.koin.compose.domain.usecase.GreetUserUseCase
import es.rafapuig.pmdm.di.users.koin.compose.domain.usecase.LoadUsersUseCase
import es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting.GreetingUserViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Definición del modulo de koin appModule
 *
 * Cada tipo de dependencia que pueda ser inyectada por koin
 * debe definirse aquí. *
 */

val appModule = module {

    /** Para inyectar un singleton UserRepository */
    single<UserRepository> { UserRepositoryListImpl() }
    // Alternativamente
    //singleOf(::UserRepositoryListImpl) { bind<UserRepository>() }

    /**
     * En el caso de objetos de casos de uso
     * es preferible crear una nueva instancia allá donde haya
     * que inyectar una dependencia (normalmente en un viewmodel)
     * Por eso indicamos que "producto" debe fabricar la factoria
     * y como fabricarlo
     */

    /** Para inyectar una instancia GreetUserUseCase */
    factoryOf(::GreetUserUseCase)
    // Alternativamente
    //factory { GreetUserUseCase(get()) }

    factoryOf(::LoadUsersUseCase)
    // Alternativamente
    //factory { LoadUsersUseCase(get()) }

    /**
     * Declarar el viewmodel para ser inyectado en un Activity o Fragment
     */
    viewModelOf(::GreetingUserViewModel)
    // Alternativamente
    //viewModel { GreetingUserViewModel(get(), get()) }

}