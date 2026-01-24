package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.data.repository.AuthRepositoryImpl
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository
import org.koin.dsl.module

val backendModule = module {

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
}

val backendModules = listOf(
    dataStoreModule,
    fakeNetworkModule,
    authRepositoryModule
)