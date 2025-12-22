package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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


@Composable
fun Counter(
    step: Int = 0
) {
    var counter by remember {
        mutableIntStateOf(0)
    }

    /**
     * Recordamos el nuevo valor que tiene el parámetro step
     * tras una recomposicion que llame al composable Counter
     * con un nuevo valor para el parámetro step
     */
    val stepState by rememberUpdatedState(newValue = step)


    Text(text = "$counter", fontSize = 128.sp)

    /**
     * Este efecto (LaunchedEffect) usa un valor: stepState
     * que cuando cambie no queremos que reinicie el efecto
     * (que no se cancele y reinicie la corrutina)
     */
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            var internalCounter = 0
            while (true) {
                delay(500.milliseconds)
                /**
                 * Con esta instrucción el valor de step
                 * se cachea al valor que tiene cuando se inicia
                 * la corrutina del LaunchedEffect
                 * Es decir, cuando se recompone el composable Counter
                 * con un nuevo valor del parametro step
                 * este nuevo valor no se usa en la ejecucion del bloque
                 * de la corrutina
                 */
                //internalCounter += step
                /**
                 * Pero si se usa el valor del estado generado
                 * por el effect rememberUpdatedState
                 * si se usa el valor actualizado en el bloque de la corrutina
                 * sin que sea necesario reiniciar la propia corrutina
                 */

                internalCounter += stepState
                counter = internalCounter
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RememberUpdateStateDemo11() {

    var counterStep by remember { mutableIntStateOf(1) }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement
                .spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Counter(counterStep)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement
                    .spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { counterStep -= 1 }
                ) {
                    Text(" -1 ")
                }
                Button(
                    onClick = { counterStep += 1 }
                ) {
                    Text(" +1 ")
                }
            }

            Text("Incremento actual: $counterStep")
        }
    }
}
