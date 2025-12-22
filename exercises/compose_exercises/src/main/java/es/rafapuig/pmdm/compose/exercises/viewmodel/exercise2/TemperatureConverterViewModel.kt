package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.compose.exercises.viewmodel.exercise3.Celsius
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
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

    fun onCelsiusTemperatureChange(value: String) {
        celsius = value
        updateFahrenheitFromCelsius()
    }


    private suspend fun celsiusToFahrenheit(celsius: String) =
        with(Celsius(celsius.toDoubleOrNull())) {
            val temperature = withContext(Dispatchers.Default) {
                toFahrenheit()
            }
            temperature?.toString() ?: EMPTY_TEMPERATURE
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


