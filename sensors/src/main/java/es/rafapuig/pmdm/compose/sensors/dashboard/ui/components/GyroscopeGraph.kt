package es.rafapuig.pmdm.compose.sensors.dashboard.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import es.rafapuig.pmdm.compose.sensors.core.domain.model.GyroscopeData
import es.rafapuig.pmdm.compose.sensors.core.ui.utils.format
import es.rafapuig.pmdm.compose.sensors.ui.components.GraphBar

@Composable
fun GyroscopeGraph(gyroscope: GyroscopeData) {
    // Giroscopio
    with(gyroscope) {
        Column {
            Text(
                "Giroscopio: " +
                        "x=${x.format(2)}, " +
                        "y=${y.format(2)}, " +
                        "z=${z.format(2)}",
                color = Color.Magenta
            )
            GraphBar(x, y, z)
        }
    }
}