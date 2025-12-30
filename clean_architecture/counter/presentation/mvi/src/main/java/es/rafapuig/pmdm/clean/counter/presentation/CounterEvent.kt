package es.rafapuig.pmdm.clean.counter.presentation

sealed interface CounterEvent {
    data class Message(val message: String) : CounterEvent
    object CounterReset : CounterEvent
}