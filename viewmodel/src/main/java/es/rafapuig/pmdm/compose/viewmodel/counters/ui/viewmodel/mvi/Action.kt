package es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.mvi

sealed interface Action {
    object Increment : Action
    object Decrement : Action
    object Reset : Action
}
