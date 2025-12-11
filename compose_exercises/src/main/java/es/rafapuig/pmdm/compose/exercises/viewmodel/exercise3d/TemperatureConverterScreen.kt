package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise3d

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.exercises.ui.theme.ComposeExercisesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun TemperatureConverterRoot(viewModel: TemperatureConverterViewModel = viewModel()) {
    ComposeExercisesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Conversor de temperatura") }
                )
            }
        ) { innerPadding ->

            val celsiusTemperature by viewModel.celsius.collectAsStateWithLifecycle()
            val fahrenheitTemperature by viewModel.fahrenheit.collectAsStateWithLifecycle()
            val isComputingFahrenheit by viewModel.isComputingFahrenheit.collectAsStateWithLifecycle()
            val isComputingCelsius by viewModel.isComputingCelsius.collectAsStateWithLifecycle()

            TemperatureConverterScreen(
                celsiusTemperature = celsiusTemperature,
                fahrenheitTemperature = fahrenheitTemperature,
                isComputingCelsius = isComputingCelsius,
                isComputingFahrenheit = isComputingFahrenheit,
                onCelsiusChange = viewModel::onCelsiusTemperatureChange,
                onFahrenheitChange = viewModel::onFahrenheitTemperatureChange,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}



@Composable
fun TemperatureConverterScreen(
    celsiusTemperature: String,
    fahrenheitTemperature: String,
    isComputingCelsius: Boolean = false,
    isComputingFahrenheit: Boolean = false,
    onCelsiusChange: (String) -> Unit = {},
    onFahrenheitChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(.6f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        TemperatureOutlinedTextField(
            value = celsiusTemperature,
            onValueChange = onCelsiusChange,
            label = "Celsius",
            suffix = "ºC",
            isComputing = isComputingCelsius
        )
        TemperatureOutlinedTextField(
            value = fahrenheitTemperature,
            onValueChange = onFahrenheitChange,
            label = "Fahrenheit",
            suffix = "ºF",
            isComputing = isComputingFahrenheit
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun TemperatureConverterScreenPreview() {
    ComposeExercisesTheme {
        Surface {
            TemperatureConverterScreen(
                celsiusTemperature = "",
                fahrenheitTemperature = "50.97",
                isComputingCelsius = true,
                isComputingFahrenheit = false
            )
        }
    }
}