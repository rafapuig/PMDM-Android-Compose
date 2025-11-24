package es.rafapuig.pmdm.compose.sensors.ui.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensors.dashboard.ui.SensorsDashBoardViewModel
import es.rafapuig.pmdm.compose.sensors.sensors_data.ui.SensorsUiState
import es.rafapuig.pmdm.compose.sensors.dashboard.ui.screens.DashboardScreen

@Composable
fun DashboardScreenRoute(viewModel: SensorsDashBoardViewModel = viewModel()) {

    val lifecycleOwner = LocalLifecycleOwner.current

    val uiState by viewModel.uiState
        .flowWithLifecycle(
            lifecycleOwner.lifecycle,
            minActiveState = Lifecycle.State.STARTED
        )
        .collectAsState(SensorsUiState())

    DashboardScreen(uiState)
}