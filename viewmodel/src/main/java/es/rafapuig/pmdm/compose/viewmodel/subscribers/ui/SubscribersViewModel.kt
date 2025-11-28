package es.rafapuig.pmdm.compose.viewmodel.subscribers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.math.cos
import kotlin.time.Duration.Companion.milliseconds

class SubscribersViewModel : ViewModel() {

    val subscribersFlow = run {
        val period = 25
        val amplitude = 10_000
        val series1 =
            generateSequence(0.0) {
                it + 0.5
            }.map {
                cos(it / period * 2 * PI + PI) * amplitude + amplitude
            }

        val numbersFlow = series1.asFlow()

        val delayedFlow = numbersFlow.transform {
            emit(it.toInt())
            delay(500.milliseconds)
        }
        delayedFlow.onStart { println("🔥 Flow frio flowId arrancado...") }
    }

    val subscribersState = subscribersFlow
        .stateIn(
            viewModelScope,
            SharingStarted.Companion.Lazily,
            0
        ).onStart { println("🔥 Flow caliente flowId arrancado...") }
}