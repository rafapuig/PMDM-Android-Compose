package es.rafapuig.pmdm.navigation.navigation3.basic.presentation.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import es.rafapuig.pmdm.navigation.navigation3.basic.domain.model.Item
import es.rafapuig.pmdm.navigation.navigation3.basic.domain.sampleItems

class ItemListViewModel() : ViewModel() {

    private val _items = mutableStateListOf(*sampleItems.toTypedArray())
    val items: List<Item> = _items

    init {
        println("ItemListViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        println("ItemListViewModel cleared")
    }

}