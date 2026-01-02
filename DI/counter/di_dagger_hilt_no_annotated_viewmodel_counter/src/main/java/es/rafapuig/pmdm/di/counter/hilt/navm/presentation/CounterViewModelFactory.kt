package es.rafapuig.pmdm.di.counter.hilt.navm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.rafapuig.pmdm.di.counter.common.domain.repositories.CounterRepository
import javax.inject.Inject

class CounterViewModelFactory @Inject constructor(
    private val repository: CounterRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CounterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}