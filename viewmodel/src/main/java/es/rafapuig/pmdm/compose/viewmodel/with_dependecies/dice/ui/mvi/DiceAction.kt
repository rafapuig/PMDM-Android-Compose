package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.mvi

sealed class DiceAction {
    object Roll : DiceAction()
}