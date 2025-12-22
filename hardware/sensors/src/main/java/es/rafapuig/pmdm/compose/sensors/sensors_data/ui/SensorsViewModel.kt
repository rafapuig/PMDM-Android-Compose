package es.rafapuig.pmdm.compose.sensors.sensors_data.ui

import kotlinx.coroutines.flow.StateFlow

interface SensorsViewModel {
    val uiState: StateFlow<SensorsUiState>
}