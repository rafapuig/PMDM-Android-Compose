package es.rafapuig.pmdm.compose.sensormotion.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.sensormotion.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensormotion.domain.accelerometerFlow
import es.rafapuig.pmdm.compose.sensormotion.domain.model.times
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn

class SensorViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Usamos el applicationContext para evitar fugas al cachear y mantener la referencia
     */
    private val appContext = application.applicationContext

    private val rawAccelerometerFlow =
        accelerometerFlow(appContext)

    val alpha = 0.15f

    /**
     * Filtro pasa-baja para suavizar los datos del acelerómetro
     */
    val accelerometerState = rawAccelerometerFlow
        .runningFold(
            AccelerometerData.Zero,
            { previous, current ->
                alpha * current + (1 - alpha) * previous
            }
        ).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            AccelerometerData.Zero
        )

    // Opcional: exposición de valores mapeados para la UI (ej: normalizar a -1..1)
    val normalized = accelerometerState
        .map {data->
            // Mapea inclinación X,Y a un rango razonable para mover la bola
            // Normalizamos dividiendo por gravedad aproximada (9.81) y cap
            val x = (data.x / 9.81f).coerceIn(-1f, 1f)
            val y = (data.y / 9.81f).coerceIn(-1f, 1f)
            Pair(x, y)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            Pair(0f, 0f)
        )

}