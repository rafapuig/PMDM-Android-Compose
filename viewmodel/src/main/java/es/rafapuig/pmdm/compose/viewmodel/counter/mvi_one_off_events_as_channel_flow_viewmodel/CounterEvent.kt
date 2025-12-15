package es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_channel_flow_viewmodel

sealed interface CounterEvent {
    data class Error(val message: String) : CounterEvent
    object CounterReset : CounterEvent
}