package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.data.repository.FakeAuthRepositoryImpl
import es.rafapuig.pmdm.clean.authentication.auth.domain.repository.AuthRepository
import org.koin.dsl.module

val fakeAuthRepositoryModule = module {

    single<AuthRepository> {
        FakeAuthRepositoryImpl()
    }
}