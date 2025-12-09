package es.rafapuig.pmdm.compose.exercises.side_effects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.pow

/**
 * Crea una pantalla donde el usuario introduce
 * el peso en kilos y la altura en centimetros
 * y calcula el IMC mediante la formula
 * IMC = peso / altura^2
 *
 * No se debe calcular el IMC salvo que sea estrictamente
 * necesario
 *
 * Pon un switch que genere recomposiciones para probar
 * que solo se realiza el cálculo cuando es necesario
 * y no en cada recomposición
 */

@Preview
@Composable
fun ComputeIMCScreen() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    val imc by remember {
        derivedStateOf {
            println("Calculando el IMC...")
            if (weight.isNotEmpty() && height.isNotEmpty()) {
                weight.toFloat() / (height.toFloat() / 100).pow(2)
            } else {
                0f
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
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
