package es.rafapuig.pmdm.di.counter.common.presentation

sealed interface CounterEvent {
    data class Message(val message: String) : CounterEvent
    object CounterReset : CounterEvent
}