package es.rafapuig.pmdm.clean.authentication.di

import es.rafapuig.pmdm.clean.authentication.auth.navigation.SessionManager
import es.rafapuig.pmdm.clean.authentication.main.presentation.MainViewModel
import es.rafapuig.pmdm.clean.authentication.main.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { SessionManager() }

    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }

}