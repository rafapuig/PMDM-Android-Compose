package es.rafapuig.pmdm.compose.lifecycle.awareness.examples.sensors.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun <T> sensorFlowLifecycleObserver(
    context: Context,
    lifecycleOwner: Lifecycle,
    sensorType: Int,
    mapper: (SensorEvent) -> T
) = callbackFlow {

        val sensorManager =
            context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensor = sensorManager.getDefaultSensor(sensorType)

        val listener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent) {
                if (event.sensor.type == sensorType) {
                    val element = mapper(event)
                    trySend(element)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        // Registramos el listener solo si la app está en foreground
        fun startListening() {
            Log.i("SensorFlowLifecycleObserver", "startListening")
            sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        }

        fun stopListening() {
            Log.i("SensorFlowLifecycleObserver", "stopListening")
            sensorManager.unregisterListener(listener)
        }

        lifecycleOwner.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                startListening()
            }

            /*override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
            }*/

            override fun onStop(owner: LifecycleOwner) {
                stopListening()
            }
        })

        awaitClose {
            stopListening()
        }
    }


