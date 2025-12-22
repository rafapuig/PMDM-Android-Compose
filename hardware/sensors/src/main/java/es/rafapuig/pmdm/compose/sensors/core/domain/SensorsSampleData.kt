package es.rafapuig.pmdm.compose.sensors.core.domain

import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

object SensorsSampleData {

    val series1 = generateSequence(0) { it + 1 }
        .map { cos(it.toFloat() / 90 * 2 * PI).toFloat() + 2f }

    val series2 = generateSequence(0) { it + 1 }
        .map { cos(it.toFloat() / 80 * 2 * PI).toFloat() }

    val series3 = generateSequence(0) { it + 1 }
        .map { sin(it.toFloat() / 100 * 2 * PI).toFloat() + 1f }


    val accelerometerSampleData =
        series1
            .zip(series2)
            .zip(series3)
            .map { (xy, z) ->
                val (x, y) = xy
                AccelerometerData(x, y, z)
            }


    val gyroscopeSampleData =
        series1
            .zip(series2)
            .zip(series3)
            .map { (xy, z) ->
                val (x, y) = xy
                GyroscopeData(x, y, z)
            }


    val lightSampleData =
        series3
            .map { LightData(it) }
}