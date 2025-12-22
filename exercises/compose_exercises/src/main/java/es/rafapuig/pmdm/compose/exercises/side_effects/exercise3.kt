package es.rafapuig.pmdm.compose.exercises.side_effects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

/**
 * Crea una pantalla que muestre el valor de un contador
 * que se incrementa automaticamente cada segundo
 * y que se inicia al iniciar la aplicación
 */

@Preview
@Composable
fun CounterAutostartScreen() {

    var counterState by remember { mutableStateOf(0) }

    suspend fun tick() {
        var counter = 0
        while (true) {
            counterState = counter
            delay(1.seconds)
            counter++
        }
    }

    LaunchedEffect(Unit) {
        tick()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            "$counterState", fontSize = 128.sp
        )
    }

}