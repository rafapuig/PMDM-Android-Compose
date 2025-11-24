package es.rafapuig.pmdm.compose.sensors.dashboard_animated_chart.ui.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensors.ui.SensorsChartDashboardViewModel
import es.rafapuig.pmdm.compose.sensors.dashboard_animated_chart.ui.screens.DashboardChartAnimatedScreen
import es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.SensorsChartUiState

@Composable
fun DashboardAnimatedChartScreenRoute(
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
        .collectAsState(SensorsChartUiState())


    DashboardChartAnimatedScreen(uiState)
}