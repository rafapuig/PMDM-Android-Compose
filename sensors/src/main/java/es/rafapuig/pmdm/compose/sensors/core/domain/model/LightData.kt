package es.rafapuig.pmdm.compose.sensors.core.domain.model

@JvmInline
value class LightData(val lux: Float) {
    companion object {
        val Zero = LightData(0f)
    }
}