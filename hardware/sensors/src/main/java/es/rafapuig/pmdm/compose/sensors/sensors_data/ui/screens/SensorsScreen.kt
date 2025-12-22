@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.sensors.sensors_data.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.sensors.core.domain.model.AccelerometerData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import es.rafapuig.pmdm.compose.sensors.sensors_data.ui.SensorsUiState
import es.rafapuig.pmdm.compose.sensors.ui.components.SensorParameterText


@Composable
fun SensorsScreen(uiState: SensorsUiState) {

    val accel = uiState.accelerometer
    val gyro = uiState.gyroscope
    val light = uiState.light

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Sensores del dispositivo",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    )
    { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val fontSize = 20.sp

            Column {
                Text("• Acelerómetro:", fontSize = 22.sp, color = Color(0xFF1E88E5))
                with(accel) {
                    mapOf("X" to x, "Y" to y, "Z" to z)
                        .forEach {
                            SensorParameterText(it.key to it.value)
                        }
                }
            }


            Column {
                Text("• Giroscopio:", fontSize = 22.sp, color = Color(0xFFD81B60))
                with(gyro) {
                    mapOf("X" to x, "Y" to y, "Z" to z)
                        .forEach { (name, value) ->
                            SensorParameterText(name, value)
                        }
                }
            }


            Column {
                Text("• Luz ambiental:", fontSize = 22.sp, color = Color(0xFF43A047))
                SensorParameterText("Lux" to light.lux)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SensorsScreenPreview() {
    SensorsScreen(
        SensorsUiState(
            accelerometer = AccelerometerData(1f, 2f, 3f),
            gyroscope = GyroscopeData(1f, 2f, 3f),
            light = LightData(113.589f)
        )
    )
}