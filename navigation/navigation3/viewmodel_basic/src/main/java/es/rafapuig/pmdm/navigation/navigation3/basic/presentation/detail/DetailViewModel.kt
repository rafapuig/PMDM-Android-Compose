package es.rafapuig.pmdm.navigation.navigation3.basic.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.rafapuig.pmdm.navigation.navigation3.basic.domain.sampleItems

class DetailViewModel(val id: Int): ViewModel() {

    val item = sampleItems.firstOrNull { it.id == id }
        ?: throw Exception("Item not found")

    init {
        println("DetailViewModel created for item id: $id")
    }

    override fun onCleared() {
        super.onCleared()
        println("DetailViewModel cleared")
    }

    class Factory(private val id: Int): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(id) as T
        }
    }

}