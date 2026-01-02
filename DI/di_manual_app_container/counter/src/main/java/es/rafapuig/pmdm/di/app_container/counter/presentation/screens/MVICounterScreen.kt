package es.rafapuig.pmdm.di.app_container.counter.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.di.app_container.counter.presentation.CounterAction
import es.rafapuig.pmdm.di.app_container.counter.presentation.CounterUiState
import es.rafapuig.pmdm.di.app_container.counter.ui.theme.PMDMComposeTheme

/**
 * El composable de la pantalla solamente recibe el estado
 * y el evento de acción para actualizarlo
 * Es mas escalable porque si añadimos nuevos elementos al estado
 * y nuevas acciones, no hace falta modificar los
 * parametros de entrada de la función composable
 */
@Composable
fun MVICounterScreen(
    uiState: CounterUiState,
    onAction: (CounterAction) -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                Alignment.CenterVertically
            )
        ) {
            Text(
                "Contador",
                style = MaterialTheme.typography.headlineMedium
            )

            val counterColor = when {
                uiState.counter > 0 -> Color.Green
                uiState.counter < 0 -> Color.Red
                else -> Color.Unspecified
            }

            Box(
                modifier = Modifier.height(90.dp),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxHeight(.9f)
                            .aspectRatio(1f),
                        strokeWidth = 6.dp
                    )
                } else {
                    Text(
                        "${uiState.counter}",
                        style = MaterialTheme.typography.displayLarge,
                        color = counterColor
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    16.dp,
                    Alignment.CenterHorizontally
                )
            ) {
                Button(onClick = { onAction(CounterAction.Decrement) }) {
                    Text(" - ")
                }
                Button(onClick = { onAction(CounterAction.Increment) }) {
                    Text(" + ")
                }
            }

            Button(onClick = { onAction(CounterAction.Reset) }) {
                Text("Reset")
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MVICounterScreenPreview() {
    PMDMComposeTheme {
        MVICounterScreen(
            uiState = CounterUiState(),
            onAction = {}
        )
    }
}