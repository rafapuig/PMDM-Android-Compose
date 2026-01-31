package es.rafapuig.pmdm.di.users.koin.compose.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module


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