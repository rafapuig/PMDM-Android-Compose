package es.rafapuig.pmdm.clean.subscribers.core.utils

import es.rafapuig.pmdm.clean.subscribers.core.domain.Sinusoidal
import es.rafapuig.pmdm.clean.subscribers.core.domain.generateSinusoidalSeries
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.time.Duration.Companion.milliseconds

fun generateSubscribersFlow(
    maxSubscribers: Int = 20_000,
    period: Float = 25f
): Flow<Int> = run {

    val series = generateSinusoidalSeries(
        sinusoidal = Sinusoidal(
            amplitude = maxSubscribers / 2.0f,
            period = period,
            phase = -PI / 2,
            offset = maxSubscribers / 2.0f
        ),
        step = 0.5f
    )

    val numbersFlow = series.asFlow()

    val delayedFlow = numbersFlow.transform {
        emit(it.toInt())
        delay(500.milliseconds)
    }

    // Flow devuelto por la lambda
    delayedFlow.onStart { println("🔥 Flow frio iniciando...") }
}