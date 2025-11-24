package es.rafapuig.pmdm.compose.sensormotion.domain

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
fun <T> sensorFlow(
    applicationContext: Context,
    sensorType: Int,
    mapper: (SensorEvent) -> T
) = callbackFlow {

    val sensorManager =
        applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    val sensor = sensorManager.getDefaultSensor(sensorType)

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

    sensorManager.registerListener(
        listener,
        sensor,
        SensorManager.SENSOR_DELAY_GAME
    )

    awaitClose { sensorManager.unregisterListener(listener) }
}