package es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_channel_flow_viewmodel

data class CounterUiState(
    val counter: Int = 0,
    val isLoading: Boolean = false,
    val message: String? = null // Si es nulo se considera que se notifica a la UI
)
