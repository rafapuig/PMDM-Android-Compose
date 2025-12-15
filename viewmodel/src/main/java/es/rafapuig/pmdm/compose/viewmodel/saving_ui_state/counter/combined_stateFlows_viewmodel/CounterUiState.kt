package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.combined_stateFlows_viewmodel

import kotlinx.parcelize.IgnoredOnParcel

data class CounterUiState(
    val counter: Int = 0,
    @IgnoredOnParcel val isLoading: Boolean = false
)
