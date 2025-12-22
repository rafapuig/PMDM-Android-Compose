package es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui

import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData

data class SensorsChartUiState(
    val accelerometerHistory: List<AccelerometerData> = emptyList(),
    val gyroscopeHistory: List<GyroscopeData> = emptyList(),
    val lightHistory: List<LightData> = emptyList()
)