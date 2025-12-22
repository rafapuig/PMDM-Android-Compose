package es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData

@Composable
fun LightChart(lightHistory: List<LightData>) {
    Column {
        Text("Luz ambiental", color = Color.Yellow)
        LineChart(
            lightHistory.map { it.lux },
            emptyList(),
            emptyList()
        )
    }
}

@Preview
@Composable
fun LightChartPreview() {
    LightChart(
        SensorsSampleData.lightSampleData
            .take(HISTORY_SIZE)
            .toList()
    )
}