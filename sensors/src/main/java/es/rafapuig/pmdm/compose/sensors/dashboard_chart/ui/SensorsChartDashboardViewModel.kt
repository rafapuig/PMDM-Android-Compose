package es.rafapuig.pmdm.compose.sensors.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import es.rafapuig.pmdm.compose.sensors.core.domain.accelerometerFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.gyroscopeFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.lightFlow
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.times
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.domain.toHistory
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.SensorsChartUiState
import kotlinx.coroutines.flow.*


class SensorsChartDashboardViewModel(context: Context) : ViewModel() {

    private val appContext = context.applicationContext
    private val alpha = 0.15f

    private val stopTimeout = 5_000L // millis

    private fun <T : Any> Flow<T>.smooth(initial: T, transform: (T, T) -> T) =
        runningFold(initial, transform)

    private fun <T> Flow<T>.toHistory(initial: List<T>) =
        toHistory(viewModelScope, stopTimeout, initial)


    val accelHistory: StateFlow<List<AccelerometerData>> =
        accelerometerFlow(appContext)
            .smooth(AccelerometerData.Zero) { prev, cur ->
                alpha * cur + (1 - alpha) * prev
            }
            .toHistory(List(HISTORY_SIZE) { AccelerometerData.Zero })


    val gyroHistory: StateFlow<List<GyroscopeData>> =
        gyroscopeFlow(appContext)
            .smooth(GyroscopeData.Zero) { prev, cur ->
                alpha * cur + (1 - alpha) * prev
            }
            .toHistory(List(HISTORY_SIZE) { GyroscopeData.Zero })


    val lightHistory: StateFlow<List<LightData>> =
        lightFlow(appContext)
            .toHistory(List(HISTORY_SIZE) { LightData(0f) })


    val uiState = combine(
        accelHistory,
        gyroHistory,
        lightHistory
    ) { a, g, l ->
        SensorsChartUiState(a, g, l)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeout),
        SensorsChartUiState()
    )


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as Application
                SensorsChartDashboardViewModel(application.applicationContext)
            }
        }
    }
}
