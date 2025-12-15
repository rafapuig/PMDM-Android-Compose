package es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.mvi

data class CounterUiState(
    val isLoading: Boolean = false,
    val counter: Int = 0
)