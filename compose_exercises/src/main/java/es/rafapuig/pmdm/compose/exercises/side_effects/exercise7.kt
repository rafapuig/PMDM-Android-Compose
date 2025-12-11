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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.mapNotNull
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
 * haz que tenga que pasar un tiempo sin escribir en una TextField
 * para que se proceda al cálculo del IMC
 *
 * Simula que el IMC tarda 1 segundo en calcularse y
 * Haz que se muestre el texto "Calculando el IMC..." mientras se calcula
 * el resultado
 *
 * Pon un switch que genere recomposiciones para probar
 * que solo se realiza el cálculo cuando es necesario
 * y no en cada recomposición
 */

private suspend fun computeIMC(weight: Float, height: Float): Float {
    delay(2.seconds)
    return weight / (height / 100).pow(2)
}

@OptIn(FlowPreview::class)
@Composable
fun ComputeIMCDebounceScreenWithLaunchedEffect(
    modifier: Modifier = Modifier
) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    var isCalculating by remember { mutableStateOf(false) }

    var imc by remember { mutableStateOf(0f) }

    println("Recomposición")

    LaunchedEffect(Unit) {

        val debouncedWeightFlow = snapshotFlow { weight }
            .drop(1)
            .mapNotNull { it.toFloatOrNull() }
            .debounce(1.seconds)


        val debouncedHeightFlow = snapshotFlow { height }
            .drop(1)
            .mapNotNull { it.toFloatOrNull() }
            .debounce(1.seconds)

        val combinedFlow =
            combine(debouncedWeightFlow, debouncedHeightFlow) { weight, height ->
                weight to height
            }

        combinedFlow.collect { (weight, height) ->
            println("Calculando el IMC... de $weight y $height")
            isCalculating = true
            imc = computeIMC(weight, height)
            isCalculating = false
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

        if (isCalculating) {
            Text("Calculando el IMC...")
        } else {
            Text("IMC: ${"%.2f".format(imc)}")
        }

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
fun ComputeIMCDebounceScreenWithLaunchedEffectPreview() {
    ComposeExercisesTheme {
        Scaffold { innerPadding ->
            ComputeIMCDebounceScreenWithLaunchedEffect(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
