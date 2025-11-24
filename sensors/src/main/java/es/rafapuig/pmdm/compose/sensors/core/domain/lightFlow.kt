package es.rafapuig.pmdm.compose.sensors.core.domain

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData


fun SensorEvent.toLightData() = LightData(
    lux = values[0]
)

fun lightFlow(context: Context) =
    sensorFlow(
        context,
        Sensor.TYPE_LIGHT
    ) { event ->
        event.toLightData()
    }

fun SensorManager.lightFlow() =
    sensorFlow(Sensor.TYPE_LIGHT) { event ->
        event.toLightData()
    }