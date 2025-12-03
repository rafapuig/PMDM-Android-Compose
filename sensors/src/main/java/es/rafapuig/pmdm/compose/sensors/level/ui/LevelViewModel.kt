package es.rafapuig.pmdm.compose.sensors.level.ui

import android.app.Application
import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.sensors.core.domain.accelerometerFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.math.abs

class LevelViewModel(application: Application) : AndroidViewModel(application) {

    val DELTA: Float = 0.05f
    val OFFSET: Float = 0.5f
    val SCALE_FACTOR: Float = 0.05f

    private fun computeAxisValue(accelerometerAxisReadingValue: Float): Float {
        return (accelerometerAxisReadingValue * SCALE_FACTOR + OFFSET)
    }

    fun isAxisCentered(value: Float, delta: Float): Boolean {
        return abs(value - 0.5f) < delta
    }

    fun isCentered(xAxis: Float, yAxis: Float): Boolean {
        return isAxisCentered(xAxis, DELTA) && isAxisCentered(yAxis, DELTA)
    }

    val sensorManager = application.getSystemService(SENSOR_SERVICE) as SensorManager

    val accelerometer = sensorManager.accelerometerFlow()

    val levelUiState = accelerometer.map { accelerometerData ->
        val xAxis = computeAxisValue(accelerometerData.x)
        val yAxis = computeAxisValue(accelerometerData.y)
        val color = if (isCentered(xAxis, yAxis)) Color.Green else Color.Red
        LevelUiState(
            xAxis = xAxis,
            yAxis = yAxis,
            color = color
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = LevelUiState()
    )

}