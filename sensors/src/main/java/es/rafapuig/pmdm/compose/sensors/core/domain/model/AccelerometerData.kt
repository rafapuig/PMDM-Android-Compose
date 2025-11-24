package es.rafapuig.pmdm.compose.sensors.core.domain.model

data class AccelerometerData(
    val x: Float,
    val y: Float,
    val z: Float
)  {

    companion object {
        val Zero = AccelerometerData(0f, 0f, 0f)
    }

    operator fun times(scalar: Float) =
        AccelerometerData(x * scalar, y * scalar, z * scalar)


    operator fun plus(other: AccelerometerData) =
        AccelerometerData(x + other.x, y + other.y, z + other.z)

}

operator fun Float.times(data: AccelerometerData) =
    AccelerometerData(this * data.x, this * data.y , this * data.z)