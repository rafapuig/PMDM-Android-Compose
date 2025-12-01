package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.domain.DiceRepository

class DiceViewModelFactory(private val repository: DiceRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiceViewModel::class.java)) {
            return DiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
