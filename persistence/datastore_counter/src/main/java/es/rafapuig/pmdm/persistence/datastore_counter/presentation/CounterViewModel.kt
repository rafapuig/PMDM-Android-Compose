package es.rafapuig.pmdm.persistence.datastore_counter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.persistence.datastore_counter.CounterApplication
import es.rafapuig.pmdm.persistence.datastore_counter.data.CounterDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CounterViewModel(private val counterDataStore: CounterDataStore) : ViewModel() {

    val counterFlow = counterDataStore.counterFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
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
        slowAction { counterDataStore.increment() }
    }

    fun decrement() {
        slowAction { counterDataStore.decrement() }
    }

    fun reset() {
        slowAction { counterDataStore.reset() }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as CounterApplication
                CounterViewModel(application.counterDataStore)
            }
        }
    }

}