package es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.presentation.list

import androidx.lifecycle.ViewModel
import es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.domain.sampleItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ItemListViewModel() : ViewModel() {

    private val _itemsFlow = MutableStateFlow(sampleItems)
    val itemsFlow = _itemsFlow.asStateFlow()


    init {
        println("ItemListViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        println("ItemListViewModel cleared")
    }

}