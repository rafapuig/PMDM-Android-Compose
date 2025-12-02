package es.rafapuig.pmdm.compose.lifecycle.sensors

import android.content.Context
import android.hardware.Sensor
import androidx.lifecycle.Lifecycle

fun accelerometerLifecycleObserver(
    lifecycle: Lifecycle,
    context: Context
) = sensorFlowLifecycleObserver(
    lifecycle = lifecycle,
    context = context,
    sensorType = Sensor.TYPE_ACCELEROMETER,
    mapper = { event ->
        AccelerometerData(
            event.values[0], event.values[1], event.values[2]
        )
    }
)
