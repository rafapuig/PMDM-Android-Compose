package es.rafapuig.pmdm.compose.viewmodel.subscribers.domain.uses_cases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.math.cos
import kotlin.time.Duration.Companion.milliseconds

object GetCurrentSubscribersUseCase {

    operator fun invoke(): Flow<Int> = run {
        val period = 25
        val amplitude = 10_000
        val series1 =
            generateSequence(0.0) {
                it + 0.5
            }.map {
                cos(it / period * 2 * PI + PI) * amplitude + amplitude
            }

        val numbersFlow = series1.asFlow()

        val delayedFlow = numbersFlow.transform {
            emit(it.toInt())
            delay(500.milliseconds)
        }
        delayedFlow.onStart { println("🔥 Flow frio iniciado...") }
    }

}