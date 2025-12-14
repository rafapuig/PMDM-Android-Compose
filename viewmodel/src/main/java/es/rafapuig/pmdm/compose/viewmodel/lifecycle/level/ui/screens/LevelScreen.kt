@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.screens

import android.app.Application
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.LevelViewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.components.AxisXPanel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.components.AxisYPanel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.components.LevelPanel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.model.isDeviceLeveled
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import kotlin.math.abs

@Composable
fun LevelScreenRoot() {

    val activity = LocalActivity.current as ComponentActivity

    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val viewModel = viewModel {
        LevelViewModel(activity.application as Application)
    }

    val accelerometerData by viewModel.acceleration.collectAsStateWithLifecycle()


    val color by remember {
        derivedStateOf {
            if (isDeviceLeveled(accelerometerData))
                Color.Green
            else
                Color.Red
        }
    }

    with(accelerometerData) {
        LevelScreen(xAxis = x, yAxis = y, color = color)
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
