@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.sensors.level.ui.screens

import android.app.Application
import android.content.res.Configuration
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.sensors.core.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.sensors.level.ui.LevelViewModel
import es.rafapuig.pmdm.compose.sensors.level.ui.components.AxisXPanel
import es.rafapuig.pmdm.compose.sensors.level.ui.components.AxisYPanel
import es.rafapuig.pmdm.compose.sensors.level.ui.components.LevelPanel
import es.rafapuig.pmdm.compose.sensors.level.ui.components.RectangularProgressIndicator

@Composable
fun LevelScreenRoot() {
    val context = LocalContext.current.applicationContext

    val viewModel = viewModel {
        val application = this[APPLICATION_KEY] as Application
        LevelViewModel(application.applicationContext)
    }

    val state by viewModel.levelUiState.collectAsStateWithLifecycle()

    with(state) {
        LevelScreen(xAxis = xAxis, yAxis = yAxis, color = color)
    }
}


@Composable
fun LevelScreen(yAxis: Float, xAxis: Float, color: Color) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Sensores - Nivel")
                }
            )
        }
    )
    { innerPadding ->
        LevelScreenContent(
            modifier = Modifier.padding(innerPadding),
            yAxis = yAxis,
            xAxis = xAxis,
            color = color
        )
    }
}


@Composable
fun LevelScreenContent(
    yAxis: Float,
    xAxis: Float,
    color: Color,
    modifier: Modifier = Modifier
) {
    val panelColumnWeight = .87f
    val panelRowWeight = .87f

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .weight(panelRowWeight)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f - panelColumnWeight)
                    .fillMaxSize()
                //.background(Color.Cyan)
            ) {
                AxisYPanel(
                    yAxis = yAxis,
                    fraction = 0.5f
                )
            }

            Box(
                modifier = Modifier
                    .weight(panelColumnWeight)
                    .fillMaxSize()
                //.background(Color.Magenta)
            ) {
                LevelPanel(
                    color = color,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .weight(1 - panelRowWeight)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f - panelColumnWeight)
                    .fillMaxSize()
                //.background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .weight(panelColumnWeight)
                    .fillMaxSize()
                //.background(Color.Blue)
            ) {
                AxisXPanel(xAxis = xAxis)
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun LevelScreenPreview() {
    PMDMComposeTheme {
        LevelScreen(
            xAxis = 0.5f,
            yAxis = 0.5f,
            color = Color.Green
        )
    }
}
