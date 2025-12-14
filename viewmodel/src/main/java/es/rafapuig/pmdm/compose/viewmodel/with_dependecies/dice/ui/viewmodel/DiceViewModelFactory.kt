package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.DiceRepository

/**
 * Factoria que para fabricar un ViewModel necesita
 * que se le inyecte la dependencia del repositorio en el constructor
 */

class DiceViewModelFactory(
    private val repository: DiceRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {

        if (modelClass.isAssignableFrom(DiceViewModel::class.java)) {

            val savedStateHandle = extras.createSavedStateHandle()

            @Suppress("UNCHECKED_CAST")
            return DiceViewModel(repository, savedStateHandle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}