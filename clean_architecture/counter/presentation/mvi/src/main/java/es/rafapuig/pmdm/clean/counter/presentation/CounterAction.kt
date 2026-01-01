package es.rafapuig.pmdm.clean.counter.presentation

sealed interface CounterAction {
    object Increment : CounterAction
    object Decrement : CounterAction
    object Reset : CounterAction
    class Set(val counter: Int) : CounterAction
}