package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun BrushTextStylingDemo() {

    val colors = listOf(Color.Red, Color.Blue, Color.Yellow, Color.Green)

    val textStyle = TextStyle(
        brush = Brush.linearGradient(colors)
    )

    Text(text = "Hello World", style = textStyle)
}