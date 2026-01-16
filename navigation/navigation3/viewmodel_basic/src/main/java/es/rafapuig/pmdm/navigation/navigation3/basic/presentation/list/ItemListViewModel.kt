package es.rafapuig.pmdm.navigation.navigation3.basic.presentation.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import es.rafapuig.pmdm.navigation.navigation3.basic.domain.model.Item
import es.rafapuig.pmdm.navigation.navigation3.basic.domain.sampleItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ItemListViewModel() : ViewModel() {

    var items = mutableStateListOf(*sampleItems.toTypedArray())
        private set

    val _itemsFlow = MutableStateFlow(sampleItems)
    val itemsFlow = _itemsFlow.asStateFlow()


    init {
        println("ItemListViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        println("ItemListViewModel cleared")
    }

}