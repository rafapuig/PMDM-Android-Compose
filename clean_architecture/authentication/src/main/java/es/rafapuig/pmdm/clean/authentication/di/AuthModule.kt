package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.AuthRemoteDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.repository.AuthRepositoryImpl
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.IsUserLoggedInUseCase
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.LoginUseCase
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.LogoutUseCase
import es.rafapuig.pmdm.clean.authentication.auth.domain.usecase.RegisterUseCase
import es.rafapuig.pmdm.clean.authentication.auth.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

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

    // Domain
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { IsUserLoggedInUseCase(get()) }

    // Presentation
    viewModel {
        LoginViewModel(get())
    }

    /*viewModel {
        RegisterViewModel(get())
    }*/
}
