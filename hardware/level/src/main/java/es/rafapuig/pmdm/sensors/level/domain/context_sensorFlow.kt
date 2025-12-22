package es.rafapuig.pmdm.sensors.level.domain

import android.content.Context
import android.hardware.SensorEvent
import android.hardware.SensorManager

/*
 * Función genérica que convierte cualquier sensor en un Flow.
 */
fun <T> Context.sensorFlow(
    sensorType: Int,
    mapper: (SensorEvent) -> T
) = with(
    applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
) {
    sensorFlow(sensorType, mapper)
}
