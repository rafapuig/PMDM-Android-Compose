package es.rafapuig.pmdm.compose.viewmodel.domain

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class SampleData {

    companion object {

        fun generateSeries(
            amplitude: Float = 1f,
            period: Int = 100,
            phase: Double = 0.0,
            offset: Float = 0f
        ) =
            generateSequence(0) { it + 1 }
                .map { it.toFloat() }
                .map { cos(it / period * 2 * PI + phase) * amplitude  + offset }
                .map { it.toFloat() }


        val series1 = generateSeries()

        val series2 = generateSeries(
            amplitude = 1.5f,
            period = 100,
            phase = PI

        )

        val series3 = generateSeries(
            amplitude = 1.2f,
            period = 200,
            phase = PI / 4
        )
    }
}