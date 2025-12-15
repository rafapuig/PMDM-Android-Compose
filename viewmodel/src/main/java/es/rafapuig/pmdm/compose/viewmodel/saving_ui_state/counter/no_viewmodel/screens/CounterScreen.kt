package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.no_viewmodel.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

@Composable
fun CounterScreen(
    counter: Int,
    isLoading: Boolean,
    onIncrement: () -> Unit = {},
    onDecrement: () -> Unit = {},
    onReset: () -> Unit = {},
) {
    Scaffold { innerPadding ->
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
                counter > 0 -> Color.Green
                counter < 0 -> Color.Red
                else -> Color.Unspecified
            }

            Box(
                modifier = Modifier.height(90.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxHeight(.9f)
                            .aspectRatio(1f),
                        strokeWidth = 6.dp
                    )
                } else {
                    Text(
                        "$counter",
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
                Button(onClick = { onDecrement() }) {
                    Text(" - ")
                }
                Button(onClick = { onIncrement() }) {
                    Text(" + ")
                }
            }

            Button(onClick = { onReset() }) {
                Text("Reset")
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterScreenPreview() {
    PMDMComposeTheme {
        CounterScreen(
            counter = 0,
            isLoading = false
        )
    }
}