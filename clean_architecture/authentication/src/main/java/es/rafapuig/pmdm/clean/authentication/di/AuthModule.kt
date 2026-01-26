package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.AuthRemoteDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.repository.AuthRepositoryImpl
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.IsUserLoggedInUseCase
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.LoginUseCase
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.LogoutUseCase
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.RegisterUseCase
import es.rafapuig.pmdm.clean.authentication.auth.presentation.login.LoginViewModel
import es.rafapuig.pmdm.clean.authentication.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authRepositoryModule = module {

    // Data
    single {
        AuthRemoteDataSource(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            remote = get(),
            local = get()
        )
    }

}


val commonAuthModule = module {

    // Domain
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factoryOf(::LogoutUseCase)
    factory { IsUserLoggedInUseCase(get()) }

    // Presentation
    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        RegisterViewModel(get())
    }
}