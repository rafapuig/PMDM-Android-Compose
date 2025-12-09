package es.rafapuig.pmdm.compose.sensors.level.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AxisYPanel(
    yAxis: Float,
    fraction: Float = 0.5f
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Eje Y: ${"%.2f".format(yAxis)}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
        )
        RectangularProgressIndicator(
            progress = yAxis,
            vertical = true,
            width = 24.dp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight(fraction)
        )
    }
}

@Preview(showBackground = true, heightDp = 600, widthDp = 60)
@Composable
fun AxisYPanelPreview() {
    AxisYPanel(yAxis = 0.5f)
}