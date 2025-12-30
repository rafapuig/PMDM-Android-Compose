package es.rafapuig.pmdm.clean.counter.presentation

data class CounterUiState(
    val counter: Int = 0,
    val isLoading: Boolean = false,
)
