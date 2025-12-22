package es.rafapuig.pmdm.sensors.level.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import es.rafapuig.pmdm.sensors.level.domain.model.AccelerometerData


fun SensorEvent.toAccelerometerData() =
    AccelerometerData(
        x = values[0],
        y = values[1],
        z = values[2]
    )

fun accelerometerFlow(context: Context) =
    context.sensorFlow(
        Sensor.TYPE_ACCELEROMETER
    ) { event ->
        event.toAccelerometerData()
    }

fun SensorManager.accelerometerFlow() =
    sensorFlow(Sensor.TYPE_ACCELEROMETER) { event ->
        event.toAccelerometerData()
    }