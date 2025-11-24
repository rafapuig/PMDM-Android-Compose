package es.rafapuig.pmdm.compose.sensors.core.domain.repositories

import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import kotlinx.coroutines.flow.Flow

interface SensorsRepository {
    fun accelerometerFlow(): Flow<AccelerometerData>
    fun gyroscopeFlow(): Flow<GyroscopeData>
    fun lightFlow(): Flow<LightData>
}