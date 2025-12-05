package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data

import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.DiceRepository
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.model.Dice
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class DiceRepositoryImpl : DiceRepository {

    override suspend fun roll(): Dice {
        delay((300..900).random().milliseconds)
        return Dice(value = (1..6).random())
    }

}