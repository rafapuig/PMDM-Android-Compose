package es.rafapuig.pmdm.compose.exercises.text.exercise1

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun PercentageText(
    percentage: Int,
    textSize : TextUnit = 32.sp,
    symbolRelativeSize : Float = 0.5f,
    symbolShiftMultiplier : Float = 0.8f,
    color: Color = Color.White,
    modifier: Modifier = Modifier
) {
    val textSize = 25.sp

    val numberFontSize = textSize
    val symbolFontSize = (textSize * symbolRelativeSize)

    Text(
        text = buildAnnotatedString {
            // 1. Añade el número con el tamaño de fuente grande
            withStyle(style = SpanStyle(fontSize = numberFontSize)) {
                append(percentage.toString())
            }
            // 2. Añade el símbolo '%' con el tamaño de fuente pequeño
            withStyle(style = SpanStyle(
                fontSize = symbolFontSize,
                baselineShift = BaselineShift(symbolShiftMultiplier)
            )) {
                append("%")
            }
        },
        color = color,
        style = MaterialTheme.typography.labelSmall
    )
}

@Preview
@Composable
fun PercentageTextPreview() {
    val percentage = 78
    PercentageText(percentage = percentage)
}

@Preview
@Composable
fun PercentageTextPreview25() {
    val percentage = 25
    PercentageText(
        percentage = percentage,
        color = Color.Red
    )
}