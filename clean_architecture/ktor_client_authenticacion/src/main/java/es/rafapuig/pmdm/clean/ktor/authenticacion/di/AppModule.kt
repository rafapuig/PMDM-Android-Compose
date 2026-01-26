package es.rafapuig.pmdm.clean.ktor.authenticacion.di


import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.data.datasource.AuthRemoteDataSource
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.data.datasource.AuthRemoteDataSourceImpl
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.data.repository.AuthRepositoryImpl
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.repository.AuthRepository
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase.LoginUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase.LogoutUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase.RefreshTokenUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.domain.usecase.RegisterUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation.LoginViewModel
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.home.presentation.HomeViewModel
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.data.ProfileRemoteDataSource
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.data.ProfileRemoteDataSourceImpl
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.data.ProfileRepositoryImpl
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.repository.ProfileRepository
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.domain.usecase.ProfileUseCase
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.presentation.ProfileViewModel
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.data.SessionDataSource
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.data.SessionDataStoreDataSource
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.data.repository.SessionRepositoryImpl
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.domain.repository.SessionRepository
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.presentation.SessionManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Session
    single<SessionDataSource> { SessionDataStoreDataSource(androidContext()) }
    single<SessionRepository> { SessionRepositoryImpl(get()) }
    single { SessionManager(get(), get(), get(), get(), get()) }

    // DataSources
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(get()) }
    single<ProfileRemoteDataSource> { ProfileRemoteDataSourceImpl(get()) }

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }

    // UseCases
    single { LoginUseCase(get(), get()) }
    single { RegisterUseCase(get(), get()) }
    single { RefreshTokenUseCase(get(), get()) }
    single { LogoutUseCase(get(), get()) }
    single { ProfileUseCase(get()) }

    // ViewModels
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}


