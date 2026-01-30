package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.DebugAuthApp
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.AuthApi
import es.rafapuig.pmdm.clean.authentication.auth.data.remote.FakeAuthApi
import es.rafapuig.pmdm.clean.authentication.core.network.BaseUrlProvider
import es.rafapuig.pmdm.clean.authentication.core.network.DebugBaseUrlProvider
import mockwebserver3.MockWebServer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val fakeNetworkModule = module {
    single<AuthApi> { FakeAuthApi() }
}

val debugNetworkModule = module {

    single<MockWebServer> {
        (androidContext() as DebugAuthApp).mockWebServer
    }

    single<BaseUrlProvider> {
        DebugBaseUrlProvider(get())
    }

}

fun platformNetworkModule() = debugNetworkModule




