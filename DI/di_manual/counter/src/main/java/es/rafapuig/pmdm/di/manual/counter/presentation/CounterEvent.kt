package es.rafapuig.pmdm.di.manual.counter.presentation

sealed interface CounterEvent {
    data class Message(val message: String) : CounterEvent
    object CounterReset : CounterEvent
}