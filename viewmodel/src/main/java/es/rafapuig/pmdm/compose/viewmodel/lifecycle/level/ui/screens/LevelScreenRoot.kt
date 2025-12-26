package es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.screens

import android.app.Application
import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.LevelViewModel
import es.rafapuig.pmdm.compose.viewmodel.lifecycle.level.ui.model.isDeviceLeveled

@Composable
fun LevelScreenRoot() {

    val activity = LocalActivity.current as ComponentActivity

    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val viewModel = viewModel {
        LevelViewModel(activity.application as Application)
    }

    val accelerometerData by viewModel.acceleration
        .collectAsStateWithLifecycle()


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