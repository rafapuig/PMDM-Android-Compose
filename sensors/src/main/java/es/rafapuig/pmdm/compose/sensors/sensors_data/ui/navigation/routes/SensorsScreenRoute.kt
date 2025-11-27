package es.rafapuig.pmdm.compose.sensors.sensors_data.ui.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensors.sensors_data.ui.SensorsViewModel
import es.rafapuig.pmdm.compose.sensors.sensors_data.ui.SensorsViewModelWithApplication
import es.rafapuig.pmdm.compose.sensors.sensors_data.ui.screens.SensorsScreen

@Composable
fun SensorsScreenRoute(
    viewModel: SensorsViewModel = viewModel<SensorsViewModelWithApplication>()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val state by viewModel.uiState
        .flowWithLifecycle(
            lifecycleOwner.lifecycle,
            minActiveState = Lifecycle.State.STARTED
        )
        .collectAsState(initial = viewModel.uiState.value)

    SensorsScreen(state)
}
