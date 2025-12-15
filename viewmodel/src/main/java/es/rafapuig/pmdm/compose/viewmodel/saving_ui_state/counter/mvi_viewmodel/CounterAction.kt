package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.mvi_viewmodel

sealed interface CounterAction {
    object Increment : CounterAction
    object Decrement : CounterAction
    object Reset : CounterAction
    class Set(val counter: Int) : CounterAction
}