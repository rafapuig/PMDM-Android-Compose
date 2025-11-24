package es.rafapuig.pmdm.compose.sensors.dashboard.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn

fun <T : Any> Flow<T>.smooth(
    scope: CoroutineScope,
    initial: T,
    stopTimeout: Long = 5000L,
    transform: (T, T) -> T
) =
    runningFold(initial, transform)
        .stateIn(
            scope,
            SharingStarted.WhileSubscribed(stopTimeout),
            initial
        )
