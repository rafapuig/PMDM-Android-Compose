package es.rafapuig.pmdm.compose.sensors.sensors_data.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import es.rafapuig.pmdm.compose.sensors.core.domain.repositories.SensorsRepository
import es.rafapuig.pmdm.compose.sensors.core.domain.repositories.SensorsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SensorsViewModelWithRepository(
    repository: SensorsRepository
) : ViewModel(),
    SensorsViewModel {

    private fun <T> Flow<T>.stateIn(initialValue: T) = stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(5_000),
        initialValue
    )

    val accelerometerState =
        repository.accelerometerFlow()
            .stateIn(AccelerometerData.Companion.Zero)

    val gyroscopeState =
        repository.gyroscopeFlow()
            .stateIn(GyroscopeData.Companion.Zero)

    val lightState =
        repository.lightFlow()
            .stateIn(LightData(0f))

    @JvmField
    val _uiState =
        combine(
            accelerometerState,
            gyroscopeState,
            lightState
        ) { a, g, l ->
            SensorsUiState(a, g, l)
        }.stateIn(
            SensorsUiState()
        )

    override val uiState: StateFlow<SensorsUiState>
        get() = _uiState


    companion object {
        /**
         * Factory para crear una instancia de [SensorsViewModelWithRepository] con un repositorio por defecto.
         */
        val Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as Application
                SensorsViewModelWithRepository(SensorsRepositoryImpl(application.applicationContext))
            }
        }

        /**
         * Factory para crear una instancia de [SensorsViewModelWithRepository] con un repositorio dado.
         */
        fun provideFactory(repository: SensorsRepository) = viewModelFactory {
            initializer {
                SensorsViewModelWithRepository(repository)
            }
        }

    }

}