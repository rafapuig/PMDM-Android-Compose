@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.viewmodel.ui.screens

import android.content.res.Configuration
import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.mvi.Action
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.mvi.CounterStateFlowMVIViewModel
import es.rafapuig.pmdm.compose.viewmodel.counters.ui.viewmodel.mvi.CounterUiState
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

private val formatter = DecimalFormat("#,###")


@Composable
fun CounterMVIScreen(
    uiState: CounterUiState,
    onAction: (Action) -> Unit = {},
    modifier: Modifier = Modifier,
    title: String = "Contador con MVI"
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(title) })
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxHeight(.8f)
                            .aspectRatio(1f),
                        strokeWidth = 6.dp
                    )
                } else {
                    Text(
                        "${formatter.format(uiState.counter)}",
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    16.dp,
                    Alignment.CenterHorizontally
                )
            ) {
                Button(onClick = { onAction(Action.Decrement) }) {
                    Text(" - ")
                }
                Button(onClick = { onAction(Action.Increment) }) {
                    Text(" + ")
                }
            }
            /**
             * Dejar para ejemplo de lo facil que es escalar con MVI
             */
            Button(onClick = { onAction(Action.Reset) }) {
                Text(" Restablecer ")
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CounterMVIScreenPreview() {

    PMDMComposeTheme {
        CounterMVIScreen(
            uiState = CounterUiState(
                true,
                0
            ),
        )
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterMVIScreenPreview_StateFlowViewModel() {

    val viewModel = viewModel<CounterStateFlowMVIViewModel>()

    val uiState by viewModel.uiState.collectAsState()
    val onAction = viewModel::dispatch

    PMDMComposeTheme {
        CounterMVIScreen(
            uiState = uiState,
            onAction = onAction
        )
    }
}

