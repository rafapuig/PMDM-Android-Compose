package es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.di

import es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.presentation.detail.DetailViewModel
import es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.presentation.list.ItemListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { ItemListViewModel() }
    viewModel { (id: Int) -> DetailViewModel(id) }

}