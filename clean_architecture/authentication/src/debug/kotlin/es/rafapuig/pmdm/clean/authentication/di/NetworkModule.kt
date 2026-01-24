package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.data.remote.AuthApi
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApi
import org.koin.dsl.module

val fakeNetworkModule = module {
    single<AuthApi> { FakeAuthApi() }
}

