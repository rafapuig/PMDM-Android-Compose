package es.rafapuig.pmdm.compose.exercises.side_effects

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.exercises.ui.theme.ComposeExercisesTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds

/**
 * Crea una pantalla donde el usuario introduce
 * el peso en kilos y la altura en centimetros
 * y calcula el IMC mediante la formula
 * IMC = peso / altura^2
 *
 * No se debe calcular el IMC salvo que sea estrictamente
 * necesario
 *
 * Como el usuario tiene que pulsar los cifras una por una
 * haz que tenga que pasar un tiempo sin escribir en un TextField
 * para que se proceda al cálculo del IMC
 *
 * Pon un switch que genere recomposiciones para probar
 * que solo se realiza el cálculo cuando es necesario
 * y no en cada recomposición
 */

private fun computeIMC(weight: Float, height: Float): Float {
    if (weight == 0f || height == 0f) return 0f
    return weight / (height / 100).pow(2)
}

@OptIn(FlowPreview::class)
@Composable
fun ComputeIMCDebounceScreen(
    modifier: Modifier = Modifier
) {

    /** Lo que escribe el usuario en el TextField para el peso */
    var weight by remember { mutableStateOf("") }

    /** Lo que escribe el usuario en el TextField para la altura */
    var height by remember { mutableStateOf("") }

    /**
     *  Convertimos el estado en un flow,
     *  cada cambio en el estado hará que el flow emita
     *  el nuevo valor
     *  Pero como aplicamos el operador debounce,
     *  antes de emitir esperará el timeout y si llega otro
     *  valor emitido no lo emitirá y esperara al timeout con el nuevo
     *  Si no llega ningún valor nuevo pasado el timeout entonces si lo emite
     */
    val debouncedWeightFlow = remember {
        println("Creando debouncedWeightFlow...")
        snapshotFlow { weight }
            .drop(1)
            .mapNotNull { it.toFloatOrNull() }
            .debounce(1.seconds)
    }

    val debouncedHeightFlow = remember {
        println("Creando debouncedHeightFlow...")
        snapshotFlow { height }
            .drop(1)
            .mapNotNull { it.toFloatOrNull() }
            .debounce(1.seconds)
    }

    val debouncedWeight by produceState(0f) {
        debouncedWeightFlow
            .onEach { println("Debounced weight: $it") }
            .collect { value = it }
    }

    val debouncedHeight by produceState(0f) {
        debouncedHeightFlow
            .onEach { println("Debounced height: $it") }
            .onEach { value = it }
            .launchIn(this)
    }


    val imc by remember {
        derivedStateOf {
            println("Calculando el valor del IMC... con debouncedWeight: $debouncedWeight y debouncedHeight: $debouncedHeight")
            computeIMC(debouncedWeight, debouncedHeight)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text("Calculadora de IMC")

        TextField(
            value = weight,
            onValueChange = {
                weight = it
            },
            label = {
                Text("Peso en kilogramos")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        TextField(
            value = height,
            onValueChange = {
                height = it
            },
            label = {
                Text("Altura en centimetros")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        Text("IMC: ${"%.2f".format(imc)}")

        var toggle by remember { mutableStateOf(false) }
        Switch(
            checked = toggle,
            onCheckedChange = {
                toggle = it
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ComputeIMCDebounceScreenPreview() {
    ComposeExercisesTheme {
        Scaffold { innerPadding ->
            ComputeIMCDebounceScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
