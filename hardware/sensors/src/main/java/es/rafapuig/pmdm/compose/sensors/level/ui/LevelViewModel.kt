package es.rafapuig.pmdm.compose.sensors.level.ui

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.sensors.core.domain.accelerometerFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.math.abs

private const val DELTA: Float = 0.05f
private const val GRAVITY_EARTH: Float = 9.80665f

const val minValue = -GRAVITY_EARTH
const val maxValue = GRAVITY_EARTH

const val range = maxValue - minValue

private fun Float.normalize(): Float {
    return (this - minValue) / range
}

private fun isAxisCentered(normalizedValue: Float): Boolean {
    return abs(normalizedValue - 0.5f) < DELTA
}

private fun isCentered(xAxis: Float, yAxis: Float): Boolean {
    return isAxisCentered(xAxis) && isAxisCentered(yAxis)
}


class LevelViewModel(context: Context) : ViewModel() {

    val sensorManager =
        context.applicationContext.getSystemService(SENSOR_SERVICE) as SensorManager

    val accelerometer = sensorManager.accelerometerFlow()

    val normalizedAccelerometerData = accelerometer.map { accelerometerData ->
        with(accelerometerData) {
            AccelerometerData(
                x = x.normalize(),
                y = y.normalize(),
                z = z.normalize()
            )
        }
    }

    val levelUiState = normalizedAccelerometerData.map { normalizedData ->
        val (xAxis, yAxis) = normalizedData
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