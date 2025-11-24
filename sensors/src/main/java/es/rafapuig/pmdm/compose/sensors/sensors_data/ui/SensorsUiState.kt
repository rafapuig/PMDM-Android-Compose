package es.rafapuig.pmdm.compose.sensors.sensors_data.ui

import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData

data class SensorsUiState(
    val accelerometer: AccelerometerData = AccelerometerData.Companion.Zero,
    val gyroscope: GyroscopeData = GyroscopeData.Companion.Zero,
    val light: LightData = LightData.Companion.Zero
)