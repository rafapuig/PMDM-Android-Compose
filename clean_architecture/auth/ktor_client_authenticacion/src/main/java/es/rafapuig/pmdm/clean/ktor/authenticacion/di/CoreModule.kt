package es.rafapuig.pmdm.clean.ktor.authenticacion.di

import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.AuthInterceptor
import es.rafapuig.pmdm.clean.ktor.authenticacion.core.network.HttpClientProvider
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientProvider.createClient() }
    single { AuthInterceptor(get()) }
}
