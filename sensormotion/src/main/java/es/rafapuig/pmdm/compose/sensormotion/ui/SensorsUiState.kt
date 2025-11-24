package es.rafapuig.pmdm.compose.sensormotion.ui

import es.rafapuig.pmdm.compose.sensormotion.domain.model.AccelerometerData

data class SensorsUiState(
    val accelerometer: AccelerometerData = AccelerometerData.Zero,
)