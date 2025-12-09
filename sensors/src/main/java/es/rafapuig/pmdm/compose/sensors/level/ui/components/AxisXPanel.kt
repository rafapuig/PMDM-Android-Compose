package es.rafapuig.pmdm.compose.sensors.level.ui.components

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AxisXPanel(xAxis: Float, fraction: Float = 0.5f) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        RectangularProgressIndicator(
            xAxis,
            height = 24.dp,
            modifier = Modifier
                .fillMaxWidth(fraction)
        )
        Text(
            text = "Eje X: ${"%.2f".format(xAxis)}"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AxisXPanelPreview() {
    AxisXPanel(xAxis = 0.5f)
}