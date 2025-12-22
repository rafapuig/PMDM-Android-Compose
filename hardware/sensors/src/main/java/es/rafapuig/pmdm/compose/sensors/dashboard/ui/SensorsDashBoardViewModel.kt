package es.rafapuig.pmdm.compose.sensors.dashboard.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.sensors.core.domain.accelerometerFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.gyroscopeFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.lightFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.times
import es.rafapuig.pmdm.compose.sensors.dashboard.domain.smooth
import es.rafapuig.pmdm.compose.sensors.sensors_data.ui.SensorsUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SensorsDashBoardViewModel(application: Application) : AndroidViewModel(application) {

    private val applicationContext = application.applicationContext

    private val stopTimeout = 5_000L // millis


    // Low-pass filter
    private val alpha = 0.15f

    private fun <T : Any> Flow<T>.smooth(
        initial: T,
        transform: (T, T) -> T
    ) =
        smooth(
            viewModelScope, initial, stopTimeout, transform
        )


    val accelerometerState: StateFlow<AccelerometerData> =
        accelerometerFlow(applicationContext)
            .smooth(AccelerometerData.Companion.Zero) { prev, cur ->
                alpha * cur + (1 - alpha) * prev
            }

    val gyroscopeState: StateFlow<GyroscopeData> =
        gyroscopeFlow(applicationContext)
            .smooth(GyroscopeData.Companion.Zero) { prev, cur ->
                alpha * cur + (1 - alpha) * prev
            }

    val lightState: StateFlow<LightData> =
        lightFlow(applicationContext)
            .stateIn(
                viewModelScope,
                SharingStarted.Companion.WhileSubscribed(stopTimeout),
                LightData(0f)
            )

    val uiState = combine(
        accelerometerState,
        gyroscopeState,
        lightState
    ) { a, g, l ->
        SensorsUiState(a, g, l)
    }.stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(stopTimeout),
        SensorsUiState()
    )

}