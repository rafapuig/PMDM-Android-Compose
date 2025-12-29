package es.rafapuig.pmdm.compose.exercises.viewmodels.navigation.exercise2.counter.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.exercises.viewmodels.ui.theme.PMDMComposeTheme

@Composable
fun CounterScreen(
    counter: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {

    val counterColor = when {
        counter > 0 -> Color.Green
        counter < 0 -> Color.Red
        else -> Color.Unspecified
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {

        Text(
            text = "Contador",
            style = MaterialTheme.typography.headlineMedium
        )

        Box(
            modifier = modifier.height(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$counter",
                style = MaterialTheme.typography.displayLarge,
                color = counterColor
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { onDecrement() }) {
                Text(text = " - ")
            }
            Button(onClick = { onIncrement() }) {
                Text(text = " + ")
            }
        }

        Button(onClick = { onReset() }) {
            Text(text = "Reset")
        }

    }

}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CounterScreenPreview() {
    PMDMComposeTheme {
        Surface {
            CounterScreen(
                counter = 0,
                onIncrement = {},
                onDecrement = {},
                onReset = {}
            )
        }
    }
}

