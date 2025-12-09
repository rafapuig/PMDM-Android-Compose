package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui

import android.os.Parcelable
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.model.Dice
import kotlinx.parcelize.Parcelize


@JvmInline
@Parcelize
value class DiceUi(val face: Int? = null) : Parcelable

fun Dice.toUi() = DiceUi(value)


@Parcelize
data class DiceUiState(
    val dice: DiceUi = DiceUi(),
    val isRolling: Boolean = false
) : Parcelable {
    companion object {
        val Empty = DiceUiState()
    }
}

sealed class DiceAction {
    object Roll : DiceAction()
}