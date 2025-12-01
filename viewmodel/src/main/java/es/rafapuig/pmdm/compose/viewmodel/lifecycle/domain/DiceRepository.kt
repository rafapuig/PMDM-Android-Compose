package es.rafapuig.pmdm.compose.viewmodel.lifecycle.domain

import es.rafapuig.pmdm.compose.viewmodel.lifecycle.domain.model.Dice

interface DiceRepository {
    suspend fun roll(): Dice
}