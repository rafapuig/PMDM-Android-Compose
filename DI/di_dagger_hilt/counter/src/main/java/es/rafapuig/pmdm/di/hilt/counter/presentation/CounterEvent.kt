package es.rafapuig.pmdm.di.hilt.counter.presentation

sealed interface CounterEvent {
    data class Message(val message: String) : CounterEvent
    object CounterReset : CounterEvent
}