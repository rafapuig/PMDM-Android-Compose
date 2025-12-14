package es.rafapuig.pmdm.compose.lifecycle.awareness.examples.sensors.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.rafapuig.pmdm.compose.lifecycle.awareness.examples.sensors.domain.AccelerometerData


@Composable
fun SensorScreen(
    accelerometerData: AccelerometerData?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val style = MaterialTheme.typography.headlineMedium
        Text("X: ${"%.3f".format( accelerometerData?.x)}", style = style)
        Text("Y: ${"%.3f".format( accelerometerData?.y)}", style = style)
        Text("Z: ${"%.3f".format( accelerometerData?.z)}", style = style)
    }
}



