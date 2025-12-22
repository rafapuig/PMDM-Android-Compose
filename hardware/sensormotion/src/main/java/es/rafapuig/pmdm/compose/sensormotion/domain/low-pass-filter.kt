package es.rafapuig.pmdm.compose.sensormotion.domain

import es.rafapuig.pmdm.compose.sensormotion.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensormotion.domain.model.times

/**
 * Filtro pasa-baja para suavizar los datos del acelerómetro
 */
fun AccelerometerData.smooth(previous: AccelerometerData, alpha: Float) =
        alpha * this + (1 - alpha) * previous