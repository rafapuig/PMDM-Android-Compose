package es.rafapuig.pmdm.compose.lifecycle.awareness.examples.sensors.domain

import android.content.Context
import android.hardware.Sensor
import androidx.lifecycle.Lifecycle

fun accelerometerLifecycleObserver(
    lifecycleOwner: Lifecycle,
    context: Context
) = sensorFlowLifecycleObserver(
    lifecycleOwner = lifecycleOwner,
    context = context,
    sensorType = Sensor.TYPE_ACCELEROMETER,
    mapper = { event ->
        AccelerometerData(
            event.values[0],
            event.values[1],
            event.values[2]
        )
    }
)
