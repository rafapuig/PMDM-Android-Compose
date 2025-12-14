package es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.model

import android.hardware.SensorEvent
import kotlin.math.absoluteValue

data class AccelerometerData(
    val x: Float,
    val y: Float,
    val z: Float
) {
    companion object {
        val ZERO = AccelerometerData(0f, 0f, 0f)
    }
}

fun SensorEvent.toAccelerometerData() = AccelerometerData(
    values[0],
    values[1],
    values[2]
)

private const val DELTA: Float = 0.05f
private const val GRAVITY_EARTH: Float = 9.80665f

const val minValue = -GRAVITY_EARTH
const val maxValue = GRAVITY_EARTH

const val range = maxValue - minValue

private fun Float.normalize(): Float {
    return (this - minValue) / range
}

fun AccelerometerData.normalize(): AccelerometerData {
    return AccelerometerData(
        x.normalize(),
        y.normalize(),
        z.normalize()
    )
}

fun isDeviceLeveled(data : AccelerometerData, delta: Float = DELTA): Boolean {
    return data.x.normalize().absoluteValue < delta &&
            data.y.normalize().absoluteValue < delta
}



