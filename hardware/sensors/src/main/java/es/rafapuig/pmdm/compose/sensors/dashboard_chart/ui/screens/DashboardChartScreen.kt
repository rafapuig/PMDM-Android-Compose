@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData
import es.rafapuig.pmdm.compose.sensors.core.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.SensorsChartUiState
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.components.AccelerometerChart
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.components.GyroscopeChart
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.components.LightChart

@Composable
fun DashboardChartScreen(
    uiState: SensorsChartUiState
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Dashboard profesional") }) }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF101010))
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(42.dp)

        ) {

            with(uiState) {
                AccelerometerChart(accelerometerHistory)
                GyroscopeChart(gyroscopeHistory)
                LightChart(lightHistory)
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun DashboardChartScreenPreview() {
    PMDMComposeTheme {
        val samples = HISTORY_SIZE
        DashboardChartScreen(
            SensorsChartUiState(
                accelerometerHistory = SensorsSampleData.accelerometerSampleData
                    .take(samples).toList(),
                gyroscopeHistory = SensorsSampleData.gyroscopeSampleData
                    .take(samples).toList(),
                lightHistory = SensorsSampleData.lightSampleData
                    .take(samples).toList()
            )
        )
    }
}
