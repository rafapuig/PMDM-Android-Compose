package es.rafapuig.pmdm.compose.viewmodel.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.viewmodel.domain.SampleData.Companion.generateSeries
import es.rafapuig.pmdm.compose.viewmodel.domain.SampleData.Companion.series1
import es.rafapuig.pmdm.compose.viewmodel.ui.components.LineChart
import es.rafapuig.pmdm.compose.viewmodel.ui.model.Series
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds


@Composable
fun ChartScreen(
    subscribersHistory: List<Int> = emptyList()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                32.dp,
                Alignment.CenterVertically
            )
    ) {
        Text(
            "Subscriptores",
            style = MaterialTheme.typography.headlineMedium
        )
        LineChart(
            Series(
                subscribersHistory.map { it.toFloat() },
                Color.Yellow
            )
        )
    }

}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview() {

    val subscribers = mutableStateListOf(0,0)

    LaunchedEffect(Unit) {
        repeat(10) {
            delay(1000.milliseconds)
            subscribers += subscribers.last() + (0..10).random()
        }
    }

    PMDMComposeTheme {
        Surface {
            ChartScreen(subscribers)
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview2() {

    val historySize = 100

    val initialList = List(historySize) {0}

    val subscribers = mutableStateListOf(*initialList.toTypedArray())

    LaunchedEffect(Unit) {
        repeat(200) {
            delay(50.milliseconds)
            subscribers += maxOf(
                0,
                subscribers.last() + (-9..10).random()
            )
            subscribers.removeFirst()
        }
    }

    PMDMComposeTheme {
        Surface {
            ChartScreen(subscribers)
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview3() {

    val historySize = 100

    val seriesFlow = remember {
        generateSeries(
            amplitude = 10_000f,
            period = 25,
            phase = PI,
            offset = 10_000f
        ).asFlow()
    }

    val initialList = List(historySize) {0}

    val subscribers = mutableStateListOf(*initialList.toTypedArray())

    LaunchedEffect(Unit) {
        seriesFlow.collect {
            delay(50.milliseconds)
            subscribers += it.toInt()
            subscribers.removeFirst()
        }
    }

    PMDMComposeTheme {
        Surface {
            ChartScreen(subscribers)
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview4() {

    val historySize = 100

    val seriesFlow = remember {
        generateSeries(
            amplitude = 10_000f,
            period = 50,
            phase = PI,
            offset = 10_000f
        ).asFlow()
    }

    val initialList = List(historySize) {0}

    val subscribers = seriesFlow
        .transform {
            delay(25.milliseconds)
            emit(it * (1 + Random.nextFloat() - 0.5f))
        }
        .scan(initialList) { history, value ->
            (history + value.toInt()).takeLast(historySize)
        }.collectAsState(initial = initialList)


    PMDMComposeTheme {
        Surface {
            ChartScreen(subscribers.value)
        }
    }
}