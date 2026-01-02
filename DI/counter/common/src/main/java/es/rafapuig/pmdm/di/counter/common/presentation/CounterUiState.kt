package es.rafapuig.pmdm.di.counter.common.presentation

data class CounterUiState(
    val counter: Int = 0,
    val isLoading: Boolean = false,
)
