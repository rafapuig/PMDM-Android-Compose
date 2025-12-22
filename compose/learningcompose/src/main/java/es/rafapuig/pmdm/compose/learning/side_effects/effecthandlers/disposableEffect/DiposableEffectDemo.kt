package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.disposableEffect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil3.request.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Counter(scope: CoroutineScope) {

    var counterState by remember { mutableStateOf(0) }

    Text(text = "$counterState", fontSize = 128.sp)

    DisposableEffect(Unit) {

        val job = scope.launch {
            var counter = 0
            while (true) {
                delay(1000)
                println("Tick desde :$this")
                counter++
                counterState = counter
            }
        }

        /** Se ejecuta cuando el composable va a salir de la composición */
        onDispose {
            println("DisposableEffectDemo onDispose")
            /** Si no cancelamos la corrutina se mantendría en ejecución */
            /** Porque el CoroutineScope es el del composable padre */
            job.cancel()
        }
    }
}


@Preview
@Composable
fun DisposableEffectDemo() {

    var toggle by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier.fillMaxSize(),
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
                Counter(scope)
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