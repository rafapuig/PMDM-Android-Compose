package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise3c

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.update
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

@JvmInline
value class Fahrenheit(val value: Double?) {
    suspend fun toCelsius(): Double? {
        delay(2.seconds)
        return value?.let { (it - 32) * 5 / 9 }
    }
}


@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class TemperatureConverterViewModel : ViewModel() {

    companion object {
        const val EMPTY_TEMPERATURE = "-"
    }

    private val _celsiusTemperature = MutableStateFlow("")
    val celsiusTemperature = _celsiusTemperature.asStateFlow()

    private val _fahrenheitTemperature = MutableStateFlow(EMPTY_TEMPERATURE)
    val fahrenheitTemperature = _fahrenheitTemperature.asStateFlow()

    private val _isComputing = MutableStateFlow(false)
    val isComputing = _isComputing.asStateFlow()

    fun onCelsiusTemperatureChange(celsius: String) {
        _celsiusTemperature.update { celsius }
    }

    init {
        observeCelsiusTemperature()
    }

    private fun observeCelsiusTemperature() {
        viewModelScope.launch {
            celsiusTemperature
                .drop(1)
                .collectLatest { value ->
                    if (value.isNotBlank()) delay(2.seconds)
                    updateFahrenheitFromCelsius(value)
                }
        }
    }

    private suspend fun celsiusToFahrenheit(celsius: String): String {
        with(Celsius(celsius.toDoubleOrNull())) {
            return withContext(Dispatchers.Default) {
                toFahrenheit()
            }?.toString() ?: EMPTY_TEMPERATURE
        }
    }

    private suspend fun updateFahrenheitFromCelsius(celsius: String) {
        _fahrenheitTemperature.update {
            try {
                _isComputing.update { true }
                if (celsius.isNotBlank()) celsiusToFahrenheit(celsius) else EMPTY_TEMPERATURE
            } finally {
                _isComputing.update { false }
            }
        }
    }


}


