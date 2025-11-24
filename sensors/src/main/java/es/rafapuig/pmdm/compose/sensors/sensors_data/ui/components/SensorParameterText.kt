package es.rafapuig.pmdm.compose.sensors.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.sensors.core.ui.utils.format

private const val defaultDecimalPlaces = 2
private val defaultFontSize = 20.sp

@Composable
fun SensorParameterText(
    name: String,
    value: Float,
    decimalPlaces: Int = defaultDecimalPlaces,
    fontSize: TextUnit = defaultFontSize
) {
    Text(
        "  $name = ${value.format(decimalPlaces)}",
        fontSize = fontSize
    )
}

@Composable
fun SensorParameterText(
    data: Pair<String, Float>,
    decimalPlaces: Int = defaultDecimalPlaces,
    fontSize: TextUnit = defaultFontSize
) {
    val (name, value) = data
    SensorParameterText(name, value, decimalPlaces, fontSize)
}

@Preview(showBackground = true)
@Composable
fun SensorParameterTextPreview() {
    SensorParameterText("Temp" to 23.456f)
}