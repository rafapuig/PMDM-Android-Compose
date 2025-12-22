package es.rafapuig.pmdm.compose.sensors.dashboard_chart.domain

import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn


fun <T> Flow<T>.toHistory(
    scope: CoroutineScope,
    stopTimeout: Long = 5000L,
    initial: List<T>
): StateFlow<List<T>> =
    scan(initial) { history, value ->
        (history + value).takeLast(HISTORY_SIZE)
    }.stateIn(
        scope,
        SharingStarted.WhileSubscribed(stopTimeout),
        initial
    )
