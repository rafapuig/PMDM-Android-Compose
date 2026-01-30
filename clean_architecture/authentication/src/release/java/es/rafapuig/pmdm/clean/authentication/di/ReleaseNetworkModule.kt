package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.core.network.BaseUrlProvider
import es.rafapuig.pmdm.clean.authentication.core.network.ReleaseBaseUrlProvider
import org.koin.dsl.module

val releaseNetworkModule = module {
    single<BaseUrlProvider> {
        ReleaseBaseUrlProvider()
    }
}

fun platformNetworkModule() = releaseNetworkModule