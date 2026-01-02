package es.rafapuig.pmdm.di.hilt.counter.presentation

data class CounterUiState(
    val counter: Int = 0,
    val isLoading: Boolean = false,
)
