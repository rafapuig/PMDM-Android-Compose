package es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.model.AccelerometerData
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.model.normalize
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.model.toAccelerometerData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LevelViewModel(application: Application) : AndroidViewModel(application) {

    private val sensorManager: SensorManager =
        application.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val accelerometer: Sensor? =
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val _acceleration = MutableStateFlow(AccelerometerData.ZERO)
    val acceleration = _acceleration.asStateFlow()

    private val listener = object : SensorEventListener {

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

        override fun onSensorChanged(event: SensorEvent) {
            // 🟢 Actualizamos el estado
            println("Sensor changed: ${event.values.toList()}")
            _acceleration.update {
                event.toAccelerometerData().normalize()
            }
        }
    }

    fun registerListener() {
        accelerometer?.let { sensor ->
            sensorManager.registerListener(
                listener,
                sensor,
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    fun unregisterListener() {
        sensorManager.unregisterListener(listener)
    }

    init {
        // 🔵 Registramos el listener
        registerListener()
    }

    override fun onCleared() {
        super.onCleared()
        // 🔴 Imprescindible o se producirán fugas de memoria
        println("LevelViewModel cleared")

        /**
         * Prueba a comentar esta linea y ver que ocurre en el LogCat
         * filtrando por -> package:mine system.out
         * (Verás que aunque salgas de la activity y se ejecute el onCleared
         * no se desregistra el listener y por tanto sigue emitiendo valores)
         */
        unregisterListener()
    }

}