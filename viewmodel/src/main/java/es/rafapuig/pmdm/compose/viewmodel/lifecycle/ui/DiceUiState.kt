package es.rafapuig.pmdm.compose.viewmodel.lifecycle.ui

import es.rafapuig.pmdm.compose.viewmodel.lifecycle.domain.model.Dice

data class DiceUiState(
    val dice: Dice,
    val isRolling: Boolean
) {
    companion object {
        val Empty = DiceUiState(Dice(), false)
    }
}

sealed class DiceAction {
    object Roll : DiceAction()
}