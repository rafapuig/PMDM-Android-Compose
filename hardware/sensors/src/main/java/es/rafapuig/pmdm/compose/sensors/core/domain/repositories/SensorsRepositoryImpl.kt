package es.rafapuig.pmdm.compose.sensors.core.domain.repositories

import android.content.Context
import android.hardware.SensorManager
import es.rafapuig.pmdm.compose.sensors.core.domain.accelerometerFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.gyroscopeFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.lightFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import kotlinx.coroutines.flow.Flow

class SensorsRepositoryImpl(
    applicationContext: Context
) : SensorsRepository {

    val sensorManager = applicationContext
        .getSystemService(Context.SENSOR_SERVICE) as SensorManager


    override fun accelerometerFlow(): Flow<AccelerometerData> =
        sensorManager.accelerometerFlow()


    override fun gyroscopeFlow(): Flow<GyroscopeData> =
        sensorManager.gyroscopeFlow()


    override fun lightFlow(): Flow<LightData> =
        sensorManager.lightFlow()

}