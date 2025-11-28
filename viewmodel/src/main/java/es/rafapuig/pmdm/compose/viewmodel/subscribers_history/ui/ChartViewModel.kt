package es.rafapuig.pmdm.compose.viewmodel.subscribers_history.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.viewmodel.domain.SampleData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class ChartViewModel : ViewModel() {

    val historySize = 100

    private val seriesFlow =
        SampleData.generateSeries(
            amplitude = 10_000f,
            period = 50,
            phase = PI,
            offset = 10_000f
        ).asFlow()

    private val initialList = List(historySize) {0}

    val subscribersHistory = seriesFlow
        .transform {
            delay(25.milliseconds)
            emit(it * (1 + Random.Default.nextFloat() - 0.5f))
        }
        .scan(initialList) { history, value ->
            (history + value.toInt()).takeLast(historySize)
        }.stateIn(
            viewModelScope,
            SharingStarted.Companion.Lazily,
            initialList
        )

}