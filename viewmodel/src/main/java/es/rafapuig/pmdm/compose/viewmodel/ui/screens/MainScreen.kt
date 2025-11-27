package es.rafapuig.pmdm.compose.viewmodel.ui.screens

import android.content.res.Configuration
import android.icu.text.DecimalFormat
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sinh
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

val formatter = DecimalFormat("#,###")


@Composable
fun MainScreen(subscribers: Int) {
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


        Text(
            "${formatter.format(subscribers)}",
            style = MaterialTheme.typography.displayLarge
        )
    }

}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainScreenPreview1() {

    var subscribers by mutableIntStateOf(1000)

    LaunchedEffect(Unit) {
        repeat(5) {
            delay(2.seconds)
            subscribers += 1
        }
    }

    PMDMComposeTheme {
        Surface {
            MainScreen(subscribers)
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainScreenPreview2() {

    val numbersFlow = flowOf(1, 2, 3, 4, 5)

    val delayedFlow = numbersFlow.map {
        delay(2.seconds)
        it
    }

    val subscribers =
        delayedFlow.collectAsState(initial = 0)

    PMDMComposeTheme {
        Surface {
            MainScreen(subscribers.value)
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainScreenPreview3() {
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
        emit(it)
        delay(500.milliseconds)
    }

    val subscribers =
        delayedFlow.collectAsState(initial = 0.0)

    PMDMComposeTheme {
        Surface {
            MainScreen(subscribers.value.toInt())
        }
    }
}
