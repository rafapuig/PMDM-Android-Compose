package es.rafapuig.pmdm.compose.learning.components.text.brushes

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun BrushTextStylingDemo1() {

    val colors =
        listOf(Color.Red, Color.Blue, Color.Yellow, Color.Green)

    val textStyle = TextStyle(
        brush = Brush.linearGradient(colors)
    )

    Text(
        text = "Hello World",
        style = textStyle
    )
}

@Preview
@Composable
fun BrushTextStylingDemo2() {

    val colors =
        listOf(Color.Red, Color.Blue, Color.Yellow, Color.Green)

    CompositionLocalProvider(
        LocalTextStyle provides
                LocalTextStyle.current
                    .copy(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold
                    )
    ) {
        val textStyle = TextStyle(
            brush = Brush.linearGradient(colors)
        ).merge(LocalTextStyle.current)

        Text(
            text = "Hello World",
            style = textStyle
        )
    }
}

@Preview
@Composable
fun BrushTextStylingDemo3() {

    val colors =
        listOf(Color.Red, Color.Blue, Color.Yellow, Color.Green)

    val textStyle = TextStyle(
        brush = Brush.linearGradient(colors)
    )

    CompositionLocalProvider(
        LocalTextStyle provides
                LocalTextStyle.current
                    .copy(fontStyle = FontStyle.Italic)
    ) {
        Text(
            text = "Hello World",
            style = textStyle.merge(LocalTextStyle.current)
        )
    }
}