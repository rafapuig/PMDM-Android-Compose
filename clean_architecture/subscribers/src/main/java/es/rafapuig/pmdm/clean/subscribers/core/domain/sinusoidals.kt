package es.rafapuig.pmdm.clean.subscribers.core.domain

import kotlin.math.PI
import kotlin.math.sin

class Sinusoidal(
    val amplitude: Float = 1f, // amplitud
    val period: Float = 1f, // periodo (tiempo que tarda en completar un ciclo
    val phase: Double = 0.0, // desplazamiento horizontal
    val offset: Float = 0f // desplazamiento vertical
) {
    fun compute(x: Float) =
        amplitude * sin(x / period * 2 * PI + phase) + offset
}

fun generateSinusoidalSeries(
    sinusoidal: Sinusoidal,
    step: Float = 0.5f,
    start: Float = 0f
    ) =
    generateSequence(start) { it + step }
        .map { sinusoidal.compute(it) }

