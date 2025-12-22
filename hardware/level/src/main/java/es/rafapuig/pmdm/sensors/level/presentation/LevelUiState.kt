package es.rafapuig.pmdm.sensors.level.presentation

import androidx.compose.ui.graphics.Color


data class LevelUiState(
    val xAxis: Float = 0f,
    val yAxis: Float = 0f,
    val color: Color = Color.Unspecified
)
