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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.domain.SampleData.Companion.generateSeries
import es.rafapuig.pmdm.compose.viewmodel.subscribers_history.components.LineChart
import es.rafapuig.pmdm.compose.viewmodel.subscribers_history.ui.ChartViewModel
import es.rafapuig.pmdm.compose.viewmodel.subscribers_history.ui.model.Series
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.isActive
import kotlin.math.PI
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds


@Composable
fun ChartScreen(
    subscribersHistory: List<Int> = emptyList(),
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
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

}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview1() {

    val subscribers = remember {
        println("Creando la lista inicial...")
        mutableStateListOf(0, 0)
    }

    LaunchedEffect(Unit) {
        while(isActive) {
            delay(500.milliseconds)
            subscribers += subscribers.last() + (-5..10).random()
            if(subscribers.size > 25) subscribers.removeFirst()
        }
    }

    ChartScreenPreview(subscribers)
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview2() {

    val subscribers = remember {
        val historySize = 100
        val initialList = List(historySize) { 0 }
        println("Creando la lista inicial...")
        mutableStateListOf(*initialList.toTypedArray())
    }

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

    ChartScreenPreview(subscribers)
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview3() {

    // 🔁 Detectar recomposiciones
    val recompositionCounter = remember { mutableStateOf(0) }
    println("🔁 Recomposición #${recompositionCounter.value}")


    // 🌱 Identificar cada Flow creado
    var flowIdCounter by remember { mutableStateOf(0) }
    val flowId = flowIdCounter

    LaunchedEffect(Unit) {
        while (true) {
            delay(3.seconds)
            recompositionCounter.value++
            flowIdCounter++
        }
    }

    /**
     * Necesitamos recordar la lista estado
     * si no, se crea el stateList en cada recomposición
     */
    val subscribers = remember {
        val historySize = 100
        val initialList = List(historySize) { 0 }
        println("Creando la lista inicial...")
        mutableStateListOf(*initialList.toTypedArray())
    }

    LaunchedEffect(Unit) {

        val seriesFlow =
            generateSeries(
                amplitude = 10_000f,
                period = 25,
                phase = PI,
                offset = 10_000f
            ).asFlow()

        seriesFlow.collect {
            delay(50.milliseconds)
            subscribers += it.toInt()
            subscribers.removeFirst()
        }
    }

    ChartScreenPreview(subscribers)
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview4() {

    // 🔁 Detectar recomposiciones
    val recompositionCounter = remember { mutableStateOf(0) }
    println("🔁 Recomposición #${recompositionCounter.value}")


    // 🌱 Identificar cada Flow creado
    var flowIdCounter by remember { mutableStateOf(0) }
    val flowId = flowIdCounter

    LaunchedEffect(Unit) {
        while (true) {
            delay(3.seconds)
            recompositionCounter.value++
            flowIdCounter++
        }
    }

    val historySize = 100

    val seriesFlow = remember {
        println("🌊 Flow creado con ID = $flowId")
        generateSeries(
            amplitude = 10_000f,
            period = 50,
            phase = PI,
            offset = 10_000f
        ).asFlow()
            .onStart {
                println("🔥 Flow $flowId arrancado")
            }
    }


    val subscribersHistoryFlow = remember(seriesFlow) {

        val initialList = List(historySize) { 0 }

        seriesFlow
            .transform {
                //println("⚙️ transform del Flow $flowId ejecutándose")
                delay(250.milliseconds)
                emit(it * (1 + Random.nextFloat() - 0.5f))
            }
            .scan(initialList) { history, value ->
                (history + value.toInt())
                    .takeLast(historySize)
            }
    }


    val subscribersHistory = subscribersHistoryFlow
        .collectAsState(
            initial = emptyList()
        )

    /**
     * Si usamos en este composable collectAsState tenemos que usar
     * El Theme en este mismo composable
     * no podemos llamar a un composable que haga uso internamente de Theme
     * porque entonces se producirá recomposición de este composable
     * por cada valor emitido por el flow
     */
    PMDMComposeTheme {
        ChartScreen(subscribersHistory.value)
    }

    //ChartScreenPreview(subscribersHistory.value)
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChartScreenPreview5() {

    // 🔁 Detectar recomposiciones
    val recompositionCounter = remember { mutableStateOf(0) }
    println("🔁 Recomposición #${recompositionCounter.value}")


    // 🌱 Identificar cada Flow creado
    var flowIdCounter by remember { mutableStateOf(0) }
    val flowId = flowIdCounter

    LaunchedEffect(Unit) {
        while (true) {
            delay(3.seconds)
            recompositionCounter.value++
            flowIdCounter++
        }
    }

    val viewModel = viewModel<ChartViewModel>()

    val subscribersHistory =
        viewModel.subscribersHistory
            .collectAsState(initial = emptyList())

    PMDMComposeTheme {
        ChartScreen(subscribersHistory.value)
    }

    /**
     * Si hacemos esto hay recomposicion provocada por el collectAsState()
     * y el Theme interno al que llama ChartScreenPreview
     */
    //ChartScreenPreview(subscribersHistory.value)

}




@Composable
fun ChartScreenPreview(subscribersHistory: List<Int>) {
    PMDMComposeTheme {
        ChartScreen(subscribersHistory)
    }
}