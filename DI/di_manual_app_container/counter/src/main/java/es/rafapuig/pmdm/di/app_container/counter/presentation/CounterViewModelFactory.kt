package es.rafapuig.pmdm.di.app_container.counter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.rafapuig.pmdm.di.app_container.counter.domain.repositories.CounterRepository

class CounterViewModelFactory(
    private val repository: CounterRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return create() as T
    }

    fun create(): CounterViewModel {
        return CounterViewModel(repository)
    }
}