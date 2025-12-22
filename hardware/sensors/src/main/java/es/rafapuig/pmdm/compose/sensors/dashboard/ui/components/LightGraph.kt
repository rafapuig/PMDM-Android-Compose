package es.rafapuig.pmdm.compose.sensors.dashboard.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import es.rafapuig.pmdm.compose.sensors.core.domain.model.LightData
import es.rafapuig.pmdm.compose.sensors.core.ui.utils.format
import es.rafapuig.pmdm.compose.sensors.ui.components.GraphBar

@Composable
fun LightGraph(light: LightData) {
    Column {
        // Luz
        Text(
            "Luz: ${light.lux.format(1)} lux",
            color = Color.Yellow
        )
        GraphBar(light.lux / 20f, 0f, 0f)
    }
}