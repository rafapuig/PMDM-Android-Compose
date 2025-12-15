package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.mvi_one_off_events_as_channel_flow_viewmodel

sealed interface CounterAction {
    object Increment : CounterAction
    object Decrement : CounterAction
    object Reset : CounterAction
    class Set(val counter: Int) : CounterAction
    //object OnMessageShown : CounterAction
}