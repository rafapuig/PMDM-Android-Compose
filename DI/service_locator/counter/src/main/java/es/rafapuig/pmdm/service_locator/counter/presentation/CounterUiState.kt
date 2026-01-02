package es.rafapuig.pmdm.service_locator.counter.presentation

data class CounterUiState(
    val counter: Int = 0,
    val isLoading: Boolean = false,
)
