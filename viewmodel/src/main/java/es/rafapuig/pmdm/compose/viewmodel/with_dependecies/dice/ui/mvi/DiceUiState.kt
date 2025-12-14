package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.mvi

import DiceUi
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiceUiState(
    val dice: DiceUi = DiceUi(),
    val isRolling: Boolean = false
) : Parcelable {
    companion object {
        val Empty = DiceUiState()
    }
}