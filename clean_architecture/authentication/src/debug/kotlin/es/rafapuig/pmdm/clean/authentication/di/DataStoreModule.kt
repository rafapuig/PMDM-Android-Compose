package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.data.datasource.AuthTokenDataSource
import es.rafapuig.pmdm.clean.authentication.auth.data.fake.FakeAuthTokenDataSource
import org.koin.dsl.module

val fakeDataStoreModule = module {

    single<AuthTokenDataSource> {
        FakeAuthTokenDataSource()
    }
}
