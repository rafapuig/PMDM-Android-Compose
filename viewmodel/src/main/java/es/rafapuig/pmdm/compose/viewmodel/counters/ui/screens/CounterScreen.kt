@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.viewmodel.counters.ui.screens

import android.content.res.Configuration
import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.CounterStateFlowViewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.CounterStateViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

private val formatter = DecimalFormat("#,###")


@Composable
fun CounterScreen(
    counter: Int,
    onIncrement: () -> Unit = {},
    title: String = "Contador"
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(title) }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(
                    32.dp,
                    Alignment.CenterVertically
                )
        ) {
            Text(
                "Contador",
                style = MaterialTheme.typography.headlineMedium
            )


            Text(
                "${formatter.format(counter)}",
                style = MaterialTheme.typography.displayLarge
            )

            Button(onClick = { onIncrement() }) {
                Text("Incrementar")
            }
        }
    }
}

/**
 * Sin ViewModel se pierden los datos al cambiar configuracion (rotar)
 */

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterScreenPreview_NoViewModel() {

    var counter by remember { mutableStateOf(0) }

    PMDMComposeTheme {
        CounterScreen(
            counter = counter,
            onIncrement = { counter++ },
            title = "Contador con State en Composable"
        )
    }
}


/**
 * Sin ViewModel se pierden los datos al cambiar configuracion (rotar)
 * a no ser que usemos rememberSaveable
 */

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterScreenPreview_NoViewModelRememberSaveable() {

    var counter by rememberSaveable() { mutableStateOf(0) }

    PMDMComposeTheme {
        CounterScreen(
            counter = counter,
            onIncrement = { counter++ },
            title = "Contador con rememeberSaveable"
        )
    }
}


/**
 * Podemos llevarnos el State a un ViewModel
 * De esta manera persiste a las recomposiciones y configuraciones *
 */

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterScreenPreview_StateViewModel() {

    val viewModel = viewModel<CounterStateViewModel>()

    PMDMComposeTheme {
        CounterScreen(
            counter = viewModel.counter,
            onIncrement = viewModel::increment,
            title = "Contador con State ViewModel"
        )
    }
}

/**
 * Podemos usar un ViewModel que mantiene el estado mediante StateFlow
 * y para usarlo en un Composable usamos collectAsStateWithLifecycle() para convertirlo
 * en un State observable de Compose
 */

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterScreenPreview_StateFlowViewModel() {

    val viewModel = viewModel<CounterStateFlowViewModel>()

    val counter by viewModel.counter.collectAsStateWithLifecycle()


    PMDMComposeTheme {
        CounterScreen(
            counter = counter,
            onIncrement = viewModel::increment,
            title = "Contador con StateFlow ViewModel"
        )
    }
}

