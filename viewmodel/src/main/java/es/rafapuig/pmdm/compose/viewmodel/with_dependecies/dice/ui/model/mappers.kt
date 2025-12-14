package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.model

import DiceUi
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.model.Dice

fun Dice.toUi() = DiceUi(value)