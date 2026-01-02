package es.rafapuig.pmdm.di.app_container.counter.presentation

sealed interface CounterEvent {
    data class Message(val message: String) : CounterEvent
    object CounterReset : CounterEvent
}