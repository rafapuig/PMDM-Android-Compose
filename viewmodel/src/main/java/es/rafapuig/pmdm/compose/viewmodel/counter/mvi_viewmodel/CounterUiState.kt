package es.rafapuig.pmdm.compose.viewmodel.counter.mvi_viewmodel

data class CounterUiState(
    val counter: Int = 0,
    val isLoading: Boolean = false
)
