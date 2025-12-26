package es.rafapuig.pmdm.clean.subscribers.viewmodel_logic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.clean.subscribers.core.domain.Sinusoidal
import es.rafapuig.pmdm.clean.subscribers.core.domain.generateSinusoidalSeries
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.time.Duration.Companion.milliseconds

class SubscribersViewModel : ViewModel() {

    val subscribersFlow = run {
        val period = 25f // segundos
        val maxSubscribers = 20_000f // numero maximo de suscriptores a alcanzar
        val series = generateSinusoidalSeries(
            sinusoidal = Sinusoidal(
                amplitude = maxSubscribers / 2,
                period = period,
                phase = -PI / 2,
                offset = maxSubscribers / 2
            ),
            step = 0.5f
        )

        val numbersFlow = series.asFlow()

        val delayedFlow = numbersFlow.transform {
            emit(it.toInt())
            delay(500.milliseconds)
        }

        // Flow devuelto por la lambda
        delayedFlow.onStart { println("🔥 Flow frio iniciando...") }
    }

    val subscribersState = subscribersFlow
        .onStart { println("🔥 Flow caliente iniciando...") }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            0
        )
}