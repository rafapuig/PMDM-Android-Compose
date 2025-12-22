package es.rafapuig.pmdm.compose.sensors.core.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData


fun SensorEvent.toGyroscopeData() =
    GyroscopeData(
        values[0],
        values[1],
        values[2]
    )

fun gyroscopeFlow(context: Context) =
    sensorFlow(
        context,
        Sensor.TYPE_GYROSCOPE
    ) { event ->
        event.toGyroscopeData()
    }

fun SensorManager.gyroscopeFlow() =
    sensorFlow(Sensor.TYPE_GYROSCOPE) { event ->
        event.toGyroscopeData()
    }