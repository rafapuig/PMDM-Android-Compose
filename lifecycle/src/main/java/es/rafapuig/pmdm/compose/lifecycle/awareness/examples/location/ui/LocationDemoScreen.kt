package es.rafapuig.pmdm.compose.lifecycle.awareness.examples.location.ui

import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationDemoScreen(
    location: Location? = null,
    modifier: Modifier = Modifier
) {

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text("Proveedor: ${location?.provider}")
            Text("Altitud: ${location?.altitude}")
            Text("Latitud: ${location?.latitude}")
            Text("Longitud: ${location?.longitude}")
            Text("Velocidad: ${location?.speed}")
            Text("Tiempo: ${location?.time}")
            Text("Accuracy: ${location?.accuracy}")
            Text("Bearing: ${location?.bearing}")
            Text("Bearing Accuracy: ${location?.bearingAccuracyDegrees}")
            Text("Vertical Accuracy: ${location?.verticalAccuracyMeters}")
            Text("Speed Accuracy: ${location?.speedAccuracyMetersPerSecond}")
            Text("Elapsed Realtime Ns: ${location?.elapsedRealtimeNanos}")
            Text("Elapsed Realtime Ms: ${location?.elapsedRealtimeUncertaintyNanos}")
        }
    }
}
