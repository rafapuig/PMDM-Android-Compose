package es.rafapuig.pmdm.clean.counter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.clean.counter.domain.RepositoryProvider
import es.rafapuig.pmdm.clean.counter.domain.repositories.CounterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CounterViewModel(private val repository: CounterRepository) : ViewModel() {

    val counterFlow = repository.counterFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Companion.WhileSubscribed(5_000L),
        initialValue = 0
    )

    val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    private fun slowAction(action: suspend () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(500)
            action()
            _isLoading.value = false
        }
    }

    fun increment() {
        slowAction { repository.increment() }
    }

    fun decrement() {
        slowAction { repository.decrement() }
    }

    fun reset() {
        slowAction { repository.reset() }
    }


    companion object {

        /** Factoria autoabastecida mediante el Service Locator RepositoryProvider */
        val Factory = viewModelFactory {
            initializer {

                val repository = RepositoryProvider.counterRepository

                CounterViewModel(repository = repository)
            }
        }
    }
}