package es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData

@Composable
fun AccelerometerChart(accelerometerHistory: List<AccelerometerData>) {
    Column {
        Text("Acelerómetro (X/Y/Z)", color = Color.Cyan)
        LineChart(
            accelerometerHistory.map { it.x },
            accelerometerHistory.map { it.y },
            accelerometerHistory.map { it.z }
        )
    }
}


@Preview
@Composable
fun AccelerometerChartPreview() {
    AccelerometerChart(
        accelerometerHistory =
            SensorsSampleData.accelerometerSampleData
                .take(HISTORY_SIZE)
                .toList()

    )
}