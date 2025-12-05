package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain

import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.model.Dice

interface DiceRepository {
    suspend fun roll(): Dice
}