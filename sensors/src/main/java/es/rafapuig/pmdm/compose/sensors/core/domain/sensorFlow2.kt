package es.rafapuig.pmdm.compose.sensors.core.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow

/*
 * Función genérica que convierte cualquier sensor en un Flow.
 */
fun <T> SensorManager.sensorFlow(
    sensorType: Int,
    mapper: (SensorEvent) -> T
) = callbackFlow {

    val sensor = getDefaultSensor(sensorType)

    if (sensor == null) {
        close(cause = IllegalArgumentException("Sensor no disponible: $sensorType"))
        return@callbackFlow
    }

    val listener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val element = mapper(event)
            trySendBlocking(element)
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    this@sensorFlow.registerListener(
        listener,
        sensor,
        SensorManager.SENSOR_DELAY_GAME
    )

    awaitClose { this@sensorFlow.unregisterListener(listener) }
}