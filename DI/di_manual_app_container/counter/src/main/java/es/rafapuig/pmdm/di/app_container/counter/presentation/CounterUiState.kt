package es.rafapuig.pmdm.di.app_container.counter.presentation

data class CounterUiState(
    val counter: Int = 0,
    val isLoading: Boolean = false,
)
