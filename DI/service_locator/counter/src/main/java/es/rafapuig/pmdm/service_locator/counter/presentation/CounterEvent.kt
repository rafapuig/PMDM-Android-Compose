package es.rafapuig.pmdm.service_locator.counter.presentation

sealed interface CounterEvent {
    data class Message(val message: String) : CounterEvent
    object CounterReset : CounterEvent
}