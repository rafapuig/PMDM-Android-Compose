package es.rafapuig.pmdm.compose.viewmodel.lifecycle.data

import es.rafapuig.pmdm.compose.viewmodel.lifecycle.domain.DiceRepository
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.domain.model.Dice
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class DiceRepositoryImpl : DiceRepository {

    override suspend fun roll(): Dice {
        delay((500..1500).random().milliseconds)
        return Dice(value = (1..6).random())
    }

}