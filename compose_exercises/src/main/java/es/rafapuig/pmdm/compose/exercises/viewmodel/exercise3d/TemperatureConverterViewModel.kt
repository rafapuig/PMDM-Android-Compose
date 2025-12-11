package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise3d

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
import java.text.DecimalFormat
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
        const val EMPTY_TEMPERATURE = ""
        const val DEBOUNCE_SECONDS_DELAY = 1
        val decimalFormatter = DecimalFormat("#.##")
    }

    private val _celsius = MutableStateFlow("")
    val celsius = _celsius.asStateFlow()

    private val _fahrenheit = MutableStateFlow(EMPTY_TEMPERATURE)
    val fahrenheit = _fahrenheit.asStateFlow()

    private val _isComputingFahrenheit = MutableStateFlow(false)
    val isComputingFahrenheit = _isComputingFahrenheit.asStateFlow()

    private val _isComputingCelsius = MutableStateFlow(false)
    val isComputingCelsius = _isComputingCelsius.asStateFlow()


    private var editingCelsius = false
    private var editingFahrenheit = false

    fun onCelsiusTemperatureChange(celsius: String) {
        _celsius.update {
            editingCelsius = true
            celsius
        }
    }

    fun onFahrenheitTemperatureChange(fahrenheit: String) {
        _fahrenheit.update {
            editingFahrenheit = true
            fahrenheit
        }
    }

    init {
        observeCelsiusTemperature()
        observeFahrenheitTemperature()
    }


    private fun observeCelsiusTemperature() {
        viewModelScope.launch {
            celsius
                .drop(1)
                .collectLatest { value ->
                    if (editingCelsius) {
                        editingCelsius = false
                        if (value.isNotBlank()) delay(DEBOUNCE_SECONDS_DELAY.seconds)
                        updateFahrenheitFromCelsius(value)
                    }
                }
        }
    }

    private fun observeFahrenheitTemperature() {
        viewModelScope.launch {
            fahrenheit
                .drop(1)
                .collectLatest { value ->
                    if (editingFahrenheit) {
                        editingFahrenheit = false
                        if (value.isNotBlank()) delay(DEBOUNCE_SECONDS_DELAY.seconds)
                        updateCelsiusFromFahrenheit(value)
                    }
                }
        }
    }





    private suspend fun celsiusToFahrenheit(celsius: String) =
        with(Celsius(celsius.toDoubleOrNull())) {
            withContext(Dispatchers.Default) {
                toFahrenheit()
            }?.let { decimalFormatter.format(it) }
                ?: EMPTY_TEMPERATURE
        }


    private suspend fun fahrenheitToCelsius(fahrenheit: String) =
        with(Fahrenheit(fahrenheit.toDoubleOrNull())) {
            withContext(Dispatchers.Default) {
                toCelsius()
            }?.let { decimalFormatter.format(it) }
                ?: EMPTY_TEMPERATURE
        }


    private suspend fun updateFahrenheitFromCelsius(celsius: String) {
        _fahrenheit.update {
            try {
                _isComputingFahrenheit.update { true }
                if (celsius.isNotBlank()) celsiusToFahrenheit(celsius) else EMPTY_TEMPERATURE
            } finally {
                _isComputingFahrenheit.update { false }
            }
        }
    }

    private suspend fun updateCelsiusFromFahrenheit(fahrenheit: String) {
        updateTemperature(
            fahrenheit,
            _celsius, ::fahrenheitToCelsius,
            _isComputingCelsius
        )
    }

    private suspend fun updateTemperature(
        value: String,
        temperature: MutableStateFlow<String>,
        converter: suspend (String) -> String,
        isComputing: MutableStateFlow<Boolean>
    ) {
        temperature.update {
            try {
                isComputing.update { true }
                if (value.isNotBlank()) converter(value) else EMPTY_TEMPERATURE
            } finally {
                isComputing.update { false }
            }
        }
    }

}



