package es.rafapuig.pmdm.sensors.level.presentation.screens

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.sensors.level.presentation.LevelViewModel
import es.rafapuig.pmdm.sensors.level.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun LevelScreenCompose() {

    PMDMComposeTheme {

        var xAxis by remember { mutableStateOf(0.5f) }
        var yAxis by remember { mutableStateOf(0.5f) }
        var color by remember { mutableStateOf(Color.Red) }

        val context = LocalContext.current

        val activity = LocalActivity.current

        LaunchedEffect(Unit) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        DisposableEffect(Unit) {

            val sensorManager = context
                .getSystemService(Context.SENSOR_SERVICE) as SensorManager

            val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

            val listener = object : SensorEventListener {

                val GRAVITY = 9.81f
                val DELTA = 0.05f

                fun Float.normalize() = (this + GRAVITY) / (GRAVITY * 2)

                fun isAxisCentered(normalizedAxis: Float) =
                    normalizedAxis in (0.5f - DELTA)..(0.5f + DELTA)

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

                override fun onSensorChanged(event: SensorEvent) {
                    xAxis = event.values[0].normalize()
                    yAxis = event.values[1].normalize()
                    color = if (isAxisCentered(xAxis) && isAxisCentered(yAxis)) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                }
            }
            sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

            onDispose {
                sensorManager.unregisterListener(listener, sensor)
            }

        }

        LevelScreen(
            xAxis = xAxis,
            yAxis = yAxis,
            color = color
        )
    }
}