package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.data.local.AuthLocalDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.local.FakeAuthLocalDataSource
import org.koin.dsl.module

val fakeDataStoreModule = module {

    single<AuthLocalDataSource> {
        FakeAuthLocalDataSource()
    }
}
