@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.sensors.dashboard.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun SensorsDashboardTopAppBar() {
    TopAppBar(
        title = { Text("Dashboard de sensores") }
    )
}