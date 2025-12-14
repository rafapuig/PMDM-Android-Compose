package es.rafapuig.pmdm.compose.lifecycle.awareness.examples.sensors.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import es.rafapuig.pmdm.compose.lifecycle.awareness.examples.sensors.domain.accelerometerLifecycleObserver

@Composable
fun SensorScreenRoot() {

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    val accelerometerData
            by accelerometerLifecycleObserver(lifecycle, context)
                .collectAsState(initial = null)

    Scaffold(
        modifier = Modifier.Companion.fillMaxSize()
    ) { innerPadding ->
        SensorScreen(
            accelerometerData,
            modifier = Modifier.padding(innerPadding)
        )
    }
}