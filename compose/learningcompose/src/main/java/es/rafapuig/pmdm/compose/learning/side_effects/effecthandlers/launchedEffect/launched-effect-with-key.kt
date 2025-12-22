package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

@Preview(showBackground = true)
@Composable
fun LaunchedEffectWithKeyDemo() {

    var rate by remember { mutableStateOf(100) }

    var elapsedTime by remember { mutableStateOf(0L) }

    /**
     * Un LaunchedEffect se ejecuta cada vez que cambia el valor de la key
     *
     * La corrutina del bloque lambda se cancela
     * y se vuelve a ejecutar con cada cambio del valor de la key
     */
    LaunchedEffect(key1 = rate) {

        val initialTime = System.currentTimeMillis()

        withContext(Dispatchers.Default) {
            while (true) {
                delay(rate.milliseconds)
                if (rate > 0)  elapsedTime = System.currentTimeMillis() - initialTime
            }
        }
    }

    /**
     * Si queremos que no se cancele usamos un LaunchedEffect con Unit
     * y rememberUpdatedState
     */

    val updatedRate by rememberUpdatedState(newValue = rate)
    var elapsedTime2 by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        val initialTime = System.currentTimeMillis()
        withContext(Dispatchers.Default) {
            while (true) {
                delay(updatedRate.milliseconds)
                if(updatedRate > 0) elapsedTime2 = System.currentTimeMillis() - initialTime
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tiempo transcurrido", fontSize = 30.sp)

        Text("Con reinicio", fontSize = 24.sp)
        Text("$elapsedTime ms", fontSize = 30.sp)

        Text("Sin reinicio", fontSize = 24.sp)
        Text("$elapsedTime2 ms", fontSize = 30.sp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { rate -= 100 }) {
                Text("-")
            }
            Text("$rate", fontSize = 30.sp)
            Button(onClick = { rate += 100 }) {
                Text("+")
            }
        }
    }

}