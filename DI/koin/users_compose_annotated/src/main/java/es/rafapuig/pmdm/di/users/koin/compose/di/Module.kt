package es.rafapuig.pmdm.di.users.koin.compose.di

import androidx.compose.runtime.Composable
import es.rafapuig.pmdm.di.users.koin.compose.data.repository.UserRepositoryListImpl
import es.rafapuig.pmdm.di.users.koin.compose.domain.repository.UserRepository
import es.rafapuig.pmdm.di.users.koin.compose.domain.usecase.GreetUserUseCase
import es.rafapuig.pmdm.di.users.koin.compose.domain.usecase.LoadUsersUseCase
import es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting.GreetingUserViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


/**
 * https://insert-koin.io/docs/quickstart/android-annotations#the-koin-module
 * @Module - Declares this class as a Koin module
 * @ComponentScan("es.rafapuig.pmdm.di.users.koin.compose") - Automatically scans and registers all Koin definitions in the package
 * @Configuration - Enables automatic module discovery when used with @KoinApplication
 */

@Module
@ComponentScan("es.rafapuig.pmdm.di.users.koin.compose")
@Configuration
class AppModule

/*
val appModule = module {

    single<UserRepository> { UserRepositoryListImpl() }
    // Alternativamente
    //singleOf(::UserRepositoryListImpl) { bind<UserRepository>() }

    single { GreetUserUseCase(get()) }

    // Aternativamente
    //singleOf(::GreetUserUseCase)

    single { LoadUsersUseCase(get()) }
    // Alternativamente
    singleOf(::LoadUsersUseCase)

    viewModelOf(::GreetingUserViewModel)
    // Alternativamente
    viewModel { GreetingUserViewModel(get(), get()) }

}
*/