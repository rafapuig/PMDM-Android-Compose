package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds


@JvmInline
value class Celsius(val value: Double?) {
    suspend fun toFahrenheit(): Double? {
        delay(2.seconds)
        return value?.let { (it * 9 / 5) + 32 }
    }
}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class TemperatureConverterViewModel : ViewModel() {

    companion object {
        const val EMPTY_TEMPERATURE = "-"
    }

    var celsius by mutableStateOf("")
        private set

    var fahrenheit by mutableStateOf(EMPTY_TEMPERATURE)
        private set

    var isComputing by mutableStateOf(false)
        private set

    init {
        snapshotFlow { celsius }
            .drop(1)
            .debounce(1.seconds)
            .onEach {
                updateFahrenheitFromCelsius()
            }.launchIn(viewModelScope)
    }

    fun onCelsiusTemperatureChange(celsius: String) {
        this.celsius = celsius
    }

    private suspend fun celsiusToFahrenheit(celsius: String): String {
        return with(Celsius(celsius.toDoubleOrNull())) {
            withContext(Dispatchers.Default) {
                toFahrenheit()
            }?.toString() ?: EMPTY_TEMPERATURE
        }
    }

    private var job: Job? = null

    private fun updateFahrenheitFromCelsius() {
        job?.cancel()
        job = viewModelScope.launch {
            isComputing = true
            ensureActive()
            fahrenheit = try {
                if (celsius.isNotBlank()) celsiusToFahrenheit(celsius)
                else EMPTY_TEMPERATURE
            } finally {
                if (isActive) isComputing = false
            }
        }
    }
}


