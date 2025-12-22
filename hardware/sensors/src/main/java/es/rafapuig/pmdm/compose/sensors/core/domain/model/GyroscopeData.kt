package es.rafapuig.pmdm.compose.sensors.core.domain.model

data class GyroscopeData(val x: Float, val y: Float, val z: Float) {

    companion object {
        val Zero = GyroscopeData(0f, 0f, 0f)
    }

    operator fun times(scalar: Float) =
        GyroscopeData(x * scalar, y * scalar, z * scalar)


    operator fun plus(other: GyroscopeData) =
        GyroscopeData(x + other.x, y + other.y, z + other.z)

}

operator fun Float.times(data: GyroscopeData) =
    GyroscopeData(this * data.x, this * data.y , this * data.z)