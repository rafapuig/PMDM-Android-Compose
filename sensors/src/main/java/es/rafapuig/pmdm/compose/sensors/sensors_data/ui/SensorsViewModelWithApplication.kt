package es.rafapuig.pmdm.compose.sensors.sensors_data.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.sensors.core.domain.accelerometerFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.gyroscopeFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.lightFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SensorsViewModelWithApplication(
    application: Application
) : AndroidViewModel(application),
    SensorsViewModel {

    private val applicationContext = application.applicationContext

    val stopTimeout = 5_000L

    val accelerometerState =
        accelerometerFlow(applicationContext).stateIn(
            viewModelScope,
            SharingStarted.Companion.WhileSubscribed(stopTimeout),
            AccelerometerData.Companion.Zero
        )


    val gyroscopeState =
        gyroscopeFlow(applicationContext).stateIn(
            viewModelScope,
            SharingStarted.Companion.WhileSubscribed(stopTimeout),
            GyroscopeData.Companion.Zero
        )


    val lightState =
        lightFlow(applicationContext).stateIn(
            viewModelScope,
            SharingStarted.Companion.WhileSubscribed(stopTimeout),
            LightData(0f)
        )


    @JvmField
    val _uiState =
        combine(
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

    override val uiState: StateFlow<SensorsUiState>
        get() = _uiState
}