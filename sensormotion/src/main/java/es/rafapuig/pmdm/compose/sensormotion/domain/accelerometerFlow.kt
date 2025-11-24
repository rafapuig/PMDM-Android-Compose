package es.rafapuig.pmdm.compose.sensormotion.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import es.rafapuig.pmdm.compose.sensormotion.domain.model.AccelerometerData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow


/**
 * Cold flow que emite lecturas del acelerómetro
 * Usar el applicationContext como argumento de llamada para evitar fugas (leaks)
 */

fun accelerometerFlow(applicationContext: Context) =
    callbackFlow<AccelerometerData> {
        val sensorManager =
            applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometer == null) {
            close(cause = IllegalArgumentException("No hay acelerómetro en el dispositivo"))
            return@callbackFlow
        }

        val listener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent) {
                val values = event.values
                val element = AccelerometerData(values[0], values[1], values[2])

                trySendBlocking(element)
                //val channelResult = trySend(element)
                /*if (channelResult.isFailure) {
                    close(cause = channelResult.exceptionOrNull())
                }*/
                //channelResult.isSuccess
                //channelResult.onSuccess {  }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

        }

        sensorManager.registerListener(
            listener,
            accelerometer,
            SensorManager.SENSOR_DELAY_GAME
        )

        awaitClose { sensorManager.unregisterListener(listener) }
    }