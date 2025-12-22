package es.rafapuig.pmdm.sensors.level.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.sensors.level.presentation.LevelViewModel

@Composable
fun LevelScreenRoot(viewModel: LevelViewModel = viewModel()) {

    val state by viewModel.levelUiState.collectAsStateWithLifecycle()

    with(state) {
        LevelScreen(xAxis = xAxis, yAxis = yAxis, color = color)
    }
}