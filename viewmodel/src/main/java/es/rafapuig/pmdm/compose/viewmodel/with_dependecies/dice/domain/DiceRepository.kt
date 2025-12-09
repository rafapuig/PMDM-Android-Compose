package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain

import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.model.Dice

/**
 * Utilizamos un interface en la capa (layer) de dominio (domain) para aplicar
 * el principio de inversión de dependencias
 */
interface DiceRepository {
    suspend fun roll(): Dice
}