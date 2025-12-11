package es.rafapuig.pmdm.compose.exercises.viewmodel.exercise2

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.exercises.ui.theme.ComposeExercisesTheme

@Preview(showSystemUi = true)
@Composable
fun TemperatureConverterRoot(viewModel: TemperatureConverterViewModel = viewModel()) {
    ComposeExercisesTheme {
        Scaffold { innerPadding ->
            TemperatureConverterScreen(
                celsiusTemperature = viewModel.celsius,
                fahrenheitTemperature = viewModel.fahrenheit,
                onCelsiusChange = viewModel::onCelsiusTemperatureChange,
                isComputing = viewModel.isComputing,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun TemperatureConverterScreen(
    celsiusTemperature: String,
    fahrenheitTemperature: String,
    isComputing: Boolean = false,
    onCelsiusChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight(.6f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        OutlinedTextField(
            value = celsiusTemperature,
            onValueChange = onCelsiusChange,
            label = { Text("Celsius") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            suffix = { Text("ºC") }
        )
        OutlinedTextField(
            readOnly = true,
            value = fahrenheitTemperature,
            onValueChange = { },
            label = { Text("Fahrenheit") },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            suffix = { Text("ºF") },
            leadingIcon = {
                if (isComputing) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(32.dp)
                            .padding(4.dp),
                        strokeWidth = 2.dp
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun TemperatureConverterScreenPreview() {
    ComposeExercisesTheme {
        Surface {
            TemperatureConverterScreen(
                celsiusTemperature = "10.5",
                fahrenheitTemperature = "50.97",
                isComputing = true
            )
        }
    }
}