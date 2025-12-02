package es.rafapuig.pmdm.compose.lifecycle.sensors

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel


class SensorViewModelFactory(
    private val lifecycle: Lifecycle,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SensorViewModel(
            lifecycle,
            context
        ) as T
    }
}


@Composable
fun SensorScreenRoot() {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    val viewModel: SensorViewModel = viewModel(
        factory = SensorViewModelFactory(lifecycle, context)
    )

    val sensorData by viewModel.sensorFlow.collectAsState(initial = null)

    SensorScreen(sensorData)
}

@Composable
fun SensorScreen(accelerometerData: AccelerometerData?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val style = MaterialTheme.typography.headlineMedium
        Text("X: ${"%.3f".format( accelerometerData?.x)}", style = style)
        Text("Y: ${"%.3f".format( accelerometerData?.y)}", style = style)
        Text("Z: ${"%.3f".format( accelerometerData?.z)}", style = style)
    }
}



