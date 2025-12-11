package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


@JvmInline
value class Celsius(val value: Double?) {
    fun toFahrenheit(): Double? {
        return value?.let { (it * 9 / 5) + 32 }
    }
}

class TemperatureConverterViewModel : ViewModel() {

    var celsius by mutableStateOf("")
        private set

    var fahrenheit by mutableStateOf("")
        private set


    fun onCelsiusTemperatureChange(value: String) {
        celsius = value
        updateFahrenheitFromCelsius()
    }

    private fun updateFahrenheitFromCelsius() {
        fahrenheit =
            if (celsius.isNotBlank()) {
                Celsius(celsius.toDoubleOrNull())
                    .toFahrenheit()?.toString() ?: ""
            } else ""
    }
}