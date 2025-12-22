package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise3a

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
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
        viewModelScope.launch {
            snapshotFlow { celsius }
                .drop(1)
                .debounce(1.seconds)
                .collectLatest {
                    updateFahrenheitFromCelsius()
                }
        }
    }

    fun onCelsiusTemperatureChange(celsius: String) {
        this.celsius = celsius
    }


    private suspend fun celsiusToFahrenheit(celsius: String) =
        with(Celsius(celsius.toDoubleOrNull())) {
            withContext(Dispatchers.Default) {
                toFahrenheit()
            }?.toString() ?: EMPTY_TEMPERATURE
        }


    private suspend fun updateFahrenheitFromCelsius() {
        isComputing = true
        currentCoroutineContext().ensureActive()
        fahrenheit = try {
            if (celsius.isNotBlank()) celsiusToFahrenheit(celsius)
            else EMPTY_TEMPERATURE
        } finally {
            if (currentCoroutineContext().isActive) isComputing = false
        }
    }
}



