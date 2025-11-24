package es.rafapuig.pmdm.compose.sensors.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GraphBar(v1: Float, v2: Float, v3: Float, modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        val widthPerBar = size.width / 3
        val factor = 20
        drawRect(
            Color.Cyan,
            topLeft = Offset(0f, size.height - v1 * factor),
            size = Size(widthPerBar - 4, v1 * factor),
            style = Stroke(4f)
        )
        drawRect(
            Color.Magenta,
            topLeft = Offset(widthPerBar, size.height - v2 * factor),
            size = Size(widthPerBar - 4, v2 * factor),
            style = Stroke(4f)
        )
        drawRect(
            Color.Yellow,
            topLeft = Offset(widthPerBar * 2, size.height - v3 * factor),
            size = Size(widthPerBar - 4, v3 * factor),
            style = Stroke(4f)
        )
    }
}

@Preview
@Composable
fun GraphBarPreview() {
    GraphBar(1f, 3f, 2f)
}

