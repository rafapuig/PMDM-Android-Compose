package es.rafapuig.pmdm.compose.sensors.core.domain

import android.content.Context
import android.hardware.SensorEvent
import android.hardware.SensorManager

/*
 * Función genérica que convierte cualquier sensor en un Flow.
 */
fun <T> sensorFlow(
    context: Context,
    sensorType: Int,
    mapper: (SensorEvent) -> T
) = with(
    context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
) { sensorFlow(sensorType, mapper) }
