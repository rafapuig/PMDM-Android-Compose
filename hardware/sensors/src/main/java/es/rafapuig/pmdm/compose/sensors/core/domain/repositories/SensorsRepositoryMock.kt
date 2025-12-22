package es.rafapuig.pmdm.compose.sensors.core.domain.repositories

import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData.accelerometerSampleData
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData.gyroscopeSampleData
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData.lightSampleData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

object SensorsRepositoryMock : SensorsRepository {

    override fun accelerometerFlow(): Flow<AccelerometerData> =
        accelerometerSampleData.asFlow()


    override fun gyroscopeFlow(): Flow<GyroscopeData> =
        gyroscopeSampleData.asFlow()


    override fun lightFlow(): Flow<LightData> =
        lightSampleData.asFlow()

}