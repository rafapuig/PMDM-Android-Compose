package es.rafapuig.pmdm.compose.sensors.dashboard.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import es.rafapuig.pmdm.compose.sensors.core.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.sensors.dashboard.ui.components.AccelerometerGraph
import es.rafapuig.pmdm.compose.sensors.dashboard.ui.components.GyroscopeGraph
import es.rafapuig.pmdm.compose.sensors.dashboard.ui.components.LightGraph
import es.rafapuig.pmdm.compose.sensors.dashboard.ui.components.SensorsDashboardTopAppBar
import es.rafapuig.pmdm.compose.sensors.sensors_data.ui.SensorsUiState


@Composable
fun DashboardScreen(
    uiState: SensorsUiState
) {
    Scaffold(
        topBar = { SensorsDashboardTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(42.dp)
        ) {
            with(uiState) {
                AccelerometerGraph(accelerometer)
                GyroscopeGraph(gyroscope)
                LightGraph(light)
            }
        }
    }
}




@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DashboardScreenPreview() {
    PMDMComposeTheme {
        DashboardScreen(
            SensorsUiState(
                accelerometer = AccelerometerData(1f, 2f, 3f),
                gyroscope = GyroscopeData(4f, 5f, 6f),
                light = LightData(135f)
            )
        )
    }
}