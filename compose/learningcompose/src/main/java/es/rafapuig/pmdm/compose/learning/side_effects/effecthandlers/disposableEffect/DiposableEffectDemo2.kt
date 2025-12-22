package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.disposableEffect

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil3.request.Disposable
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Sensor() {
    var counter by remember { mutableStateOf(0) }

    Text(text = "$counter", fontSize = 128.sp)

    val context = LocalContext.current

    DisposableEffect(Unit) {

        val sensorManager =
            context.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        val listener = object : SensorEventListener {

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

            override fun onSensorChanged(event: SensorEvent?) {
                println("onSensorChanged...")
                event?.let {
                    counter = it.values[0].toInt()
                }
            }
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        /**
         * Se ejecuta cuando el composable abandona la composición
         */
        onDispose {
            println("DisposableEffectDemo2 onDispose")
            /** Si no desregistramos aqui el listener se seguiran produciendo llamadas a onSensorChanged */
            //sensorManager.unregisterListener(listener)
        }
    }

}


@Composable
fun DisposableEffectDemo2(modifier: Modifier = Modifier) {

    var toggle by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (toggle) {
                Sensor()
            }
        }
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { toggle = !toggle }) {
                Text(text = if (toggle) "Stop" else "Start")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DisposableEffectDemo2Preview() {
    PMDMComposeTheme {
        Scaffold { innerPadding ->
            DisposableEffectDemo2(Modifier.padding(innerPadding))
        }
    }
}