package es.rafapuig.pmdm.clean.subscribers.presentation.screens

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.clean.subscribers.core.presentation.screens.SubscribersScreen
import es.rafapuig.pmdm.clean.subscribers.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.clean.subscribers.viewmodel_logic.presentation.SubscribersViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onStart
import kotlin.time.Duration.Companion.seconds


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
            println("🔥 Flow $flowId iniciado...")
        }
    }


    var subscribers by remember { mutableIntStateOf(0) }

    LaunchedEffect(subscribersFlow) {
        subscribersFlow.collect {
            println("⚙️ subscribersFlow ha emitido: $it")
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