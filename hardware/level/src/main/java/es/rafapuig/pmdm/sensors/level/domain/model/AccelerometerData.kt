package es.rafapuig.pmdm.sensors.level.domain.model

data class AccelerometerData(
    val x: Float,
    val y: Float,
    val z: Float
) {

    companion object {
        val Zero = AccelerometerData(0f, 0f, 0f)

        enum class Axis {
            X, Y, Z
        }

        private const val DELTA: Float = 0.05f // 5% de margen de error

        private const val GRAVITY_EARTH: Float = 9.80665f

        private const val minValue = -GRAVITY_EARTH
        private const val maxValue = GRAVITY_EARTH

        private const val range = maxValue - minValue

        private fun Float.normalize(): Float {
            return (this - minValue) / range
        }
    }

    val isCenteredX get() = isCentered(Axis.X)
    val isCenteredY get() = isCentered(Axis.Y)
    val isCenteredZ get() = isCentered(Axis.Z)

    fun isCentered(axis: Axis): Boolean {
        val axisValue = when (axis) {
            Axis.X -> x
            Axis.Y -> y
            Axis.Z -> z
        }
        return axisValue.normalize() in (0.5f - DELTA)..(0.5f + DELTA)
    }

    fun normalize() =
        AccelerometerData(
            x.normalize(),
            y.normalize(),
            z.normalize()
        )

    operator fun times(scalar: Float) =
        AccelerometerData(x * scalar, y * scalar, z * scalar)


    operator fun plus(other: AccelerometerData) =
        AccelerometerData(x + other.x, y + other.y, z + other.z)

}

operator fun Float.times(data: AccelerometerData) =
    AccelerometerData(this * data.x, this * data.y, this * data.z)