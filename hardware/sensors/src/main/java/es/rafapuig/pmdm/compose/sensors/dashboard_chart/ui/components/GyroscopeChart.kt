package es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData

@Composable
fun GyroscopeChart(gyroscopeHistory:List<GyroscopeData>) {
    Column {
        Text("Giroscopio (X/Y/Z)", color = Color.Magenta)
        LineChart(
            gyroscopeHistory.map { it.x },
            gyroscopeHistory.map { it.y },
            gyroscopeHistory.map { it.z }
        )
    }
}

@Preview
@Composable
fun GyroscopeChartPreview() {
    GyroscopeChart(
         SensorsSampleData.gyroscopeSampleData
             .take(HISTORY_SIZE)
             .toList()
    )
}