package es.rafapuig.pmdm.compose.viewmodel.subscribers.ui.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.subscribers.ui.SubscribersViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlin.math.PI
import kotlin.math.cos
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

private val formatter = DecimalFormat("#,###")


@Composable
fun SubscribersScreen(subscribers: Int) {
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
fun SubscribersScreenPreview_State_LaunchEffect() {

    var subscribers by remember { mutableIntStateOf(100) }

    LaunchedEffect(Unit) {
        repeat(5) {
            delay(2.seconds)
            subscribers += 1
        }
    }

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers)
        }
    }
}


/**
 * Ejemplos con flows
 */


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewFlow_State_LaunchEffect() {

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
     * Cambiar remember por run y ver como crea un nuevo Flow en cada recomposicion
     */
    val numbersFlow = remember {
        println("🌊 Flow creado con ID = $flowIdCounter")
        flowOf(1, 2, 3, 4, 5)
    }

    val subscribers = remember { mutableIntStateOf(0) }

    LaunchedEffect(numbersFlow) {
        numbersFlow
            .map {
                delay(2.seconds)
                it
            }
            .onStart { println("🔥 Flow $flowId arrancado...") }
            .collect {
                println("⚙️ Flow $flowId recoge $it")
                subscribers.value = it
            }
    }

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers.value)
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewFlow_ProduceState() {

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
     * Cambiar remember por run y ver como crea un nuevo Flow en cada recomposicion
     */
    val numbersFlow = remember {
        println("🌊 Flow creado con ID = $flowId")
        flowOf(1, 2, 3, 4, 5)
    }

    val subscribers = produceState(0, numbersFlow) {
        numbersFlow
            .map {
                delay(2.seconds)
                it
            }
            .onStart { println("🔥 Flow $flowId arrancado...") }
            .collect {
                println("⚙️ Flow $flowId recoge $it")
                this.value = it // Value es el valor del estado producido por produceState
            }
    }

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers.value)
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewFlow_ProduceState2() {

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
     * Cambiar remember por run crea un nuevo Flow en cada recomposicion
     */
    val numbersFlow = run {
        println("🌊 Flow creado con ID = $flowId")
        flowOf(1, 2, 3, 4, 5)
    }

    /**
     * Pero ahora produce no tiene en cuenta como clave el cambio de numbersFlow
     * por tanto, sigue ejecutando la misma corrutina que inicio con valor 0
     * aun que cambie el numbersFlow en cada recomposición
     */
    val subscribers = produceState(0) {
        numbersFlow
            .map {
                delay(2.seconds)
                it
            }
            .onStart { println("🔥 Flow $flowId arrancado...") }
            .collect {
                println("⚙️ Flow $flowId recoge $it")
                this.value = it // Value es el valor del estado producido por produceState
            }
    }

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers.value)
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewFlow_ProduceState3() {

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
     * Crear el flow dentro del cuerpo de la lambda de producerState
     * tambien nos asegura que no se vuelve a crear el flow ni reinicia la recolección
     */
    val subscribers = produceState(0) {
            println("🌊 Flow creado con ID = $flowId")

            flowOf(1, 2, 3, 4, 5)
            .map {
                delay(2.seconds)
                it
            }
            .onStart { println("🔥 Flow $flowId arrancado...") }
            .collect {
                println("⚙️ Flow $flowId recoge $it")
                this.value = it // Value es el valor del estado producido por produceState
            }
    }

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers.value)
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewFlow_CollectAsState() {

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
     * Cambiar remember por run y ver como crea un nuevo Flow en cada recomposicion
     */
    val numbersFlow = remember {
        println("🌊 Flow creado con ID = $flowIdCounter")
        flowOf(1, 2, 3, 4, 5)
            .map {
                delay(2.seconds)
                it
            }
            .onStart { println("🔥 Flow $flowId arrancado...") }
    }

    /**
     * collectAsState equivale a llamar a produceState con initial = 0
     * y key1 = numbersFlow (el receiver de collectAsState)
     * Por eso, el receiver tiene que ser un objeto recordado (remember)
     * o venir de un ViewModel
     */
    val subscribers = numbersFlow.collectAsState(0)

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers.value)
        }
    }
}


/**
 * Ejemplos con series (flows infinitos)
 */


fun getSeriesFlow() = run {
    val period = 25
    val amplitude = 10_000
    val series1 =
        generateSequence(0.0) {
            it + 0.5
        }.map {
            cos(it / period * 2 * PI + PI) * amplitude + amplitude
        }

    series1.asFlow()
}

fun <T> Flow<T>.delayed(duration: Duration) = transform {
    emit(it)
    delay(duration)
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewWithLocalFlow_State_LaunchEffect() {

    val subscribersFlow = remember {
        getSeriesFlow()
            .delayed(500.milliseconds)
            .map { it.toInt() }
    }

    var subscribers by remember { mutableIntStateOf(0) }

    LaunchedEffect(subscribersFlow) {
        subscribersFlow.collect {
            println("⚙️ subscribersFlow recoge $it")
            subscribers = it
        }
    }

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers)
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewWithLocalFlow_CollectAsState() {

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


    val subscribersFlow = remember {
        println("🌊 Flow creado con ID = $flowId")
        getSeriesFlow()
            .delayed(500.milliseconds)
            .map { it.toInt() }
            .onStart { println("🔥 Flow frio $flowId arrancado...") }
    }


    val subscribers by subscribersFlow
        //.onStart { println("🔥 Flow caliente $flowId arrancado...") } // si añado esto recompone
        .collectAsState(0)

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers)
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewWithViewModel_State_LaunchEffect() {

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


    val viewModel = viewModel<SubscribersViewModel>()

    val subscribersFlow = remember {
        println("🌊 Flow creado con ID = $flowId")
        viewModel.subscribersFlow.onStart {
            println("🔥 Flow $flowId arrancado...")
        }
    }


    var subscribers by remember { mutableIntStateOf(0) }

    LaunchedEffect(subscribersFlow) {
        subscribersFlow.collect {
            println("⚙️ subscribersFlow recoge $it")
            subscribers = it
        }
    }

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers)
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SubscribersScreenPreviewWithViewModel_CollectAsState() {

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


    val viewModel = viewModel<SubscribersViewModel>()

    val subscribersFlow = remember {
        println("🌊 Flow creado con ID = $flowId")
        viewModel.subscribersFlow.onStart {
            println("🔥 Flow $flowId arrancado...")
        }
    }


    val subscribers by remember {
        subscribersFlow
            .onStart { println("🔥 Flow $flowId arrancado...") }
    } // Si le hacemos algo al flow, recompone a no ser que lo recordemos
        .collectAsState(0)

    PMDMComposeTheme {
        Surface {
            SubscribersScreen(subscribers)
        }
    }
}

