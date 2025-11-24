package es.rafapuig.pmdm.compose.sensors.ui.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.SensorsChartUiState
import es.rafapuig.pmdm.compose.sensors.ui.SensorsChartDashboardViewModel
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.screens.DashboardChartScreen

@Composable
fun DashboardChartScreenRoute(
    viewModel: SensorsChartDashboardViewModel = viewModel(
        factory = SensorsChartDashboardViewModel.Factory
    )
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val uiState by viewModel.uiState
        .flowWithLifecycle(
            lifecycleOwner.lifecycle,
            minActiveState = Lifecycle.State.STARTED
        )
        .collectAsState(initial = SensorsChartUiState())

    DashboardChartScreen(uiState)
}