package es.rafapuig.pmdm.compose.lifecycle.sensors

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel

class SensorViewModel(
    private val lifecycle: Lifecycle,
    private val context: Context
): ViewModel() {
    val sensorFlow = accelerometerLifecycleObserver(lifecycle, context)

}