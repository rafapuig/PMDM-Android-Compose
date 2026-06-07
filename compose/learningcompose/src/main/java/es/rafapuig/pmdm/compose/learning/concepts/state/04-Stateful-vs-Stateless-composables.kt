package es.rafapuig.pmdm.compose.learning.concepts.state

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun MyStatefulSwitch() {
    /**
     * Dentro del composable se declara un estado
     * lo que convierte al composable en un composable con estado (Stateful composable)
     */
    val checkedState =
        remember { mutableStateOf(false) }

    Switch(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}

@Composable
fun MyStatelessSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange
    )
}

@Composable
fun MySwitch() {
    val checkedState = remember { mutableStateOf(false) }
    MyStatelessSwitch(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}

@Preview
@Composable
fun MyStatefulAlternateButton() {
    val enabledState = remember { mutableStateOf(false) }

    Button(
        onClick = { enabledState.value = !enabledState.value }
    ) {
        Text(if (enabledState.value) "ON" else "OFF")
    }
}


@Composable
fun MyStatelessAlternateButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(if (enabled) "ON" else "OFF")
    }
}

@Preview
@Composable
fun MyAlternateButton() {
    val enabledState = remember { mutableStateOf(false) }
    MyStatelessAlternateButton(
        enabled = enabledState.value,
        onClick = { enabledState.value = !enabledState.value }
    )
}

@Preview
@Composable
fun StatefulColorBox() {

    fun Color.Companion.random() =
        with(Random.Default) {
            Color(
                red = nextFloat(),
                green = nextFloat(),
                blue = nextFloat(),
                1f
            )
        }

    var color by remember {
        mutableStateOf(Color.Yellow)
    }

    Box(
        modifier = Modifier
            .background(color)
            .size(300.dp)
            .clickable { color = Color.random() }
    )
}


/**
 * Este composable no tiene estado,
 *  su resultado solamente depende del valor de los parámetros que recibe
 *  del llamador
 */

@Composable
fun StatelessColorBox(
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color)
            .size(300.dp)
            .clickable { onClick() }
    )
}


@Preview
@Composable
fun StatelessColorBoxCaller() {

    fun Color.Companion.random() =
        with(Random.Default) {
            Color(
                red = nextFloat(),
                green = nextFloat(),
                blue = nextFloat(),
                1f
            )
        }

    /**
     * Declaramos un estado mutable que contiene el color
     * que debe mostrar el composable StatelessColorBox
     */
    val color = remember {
        mutableStateOf(Color.Cyan)
    }

    /**
     * Pasamos al stateless composable los valores que necesita
     * - el color que debe mostrar
     * - la acción que debe realizar cuando se haga click
     */
    StatelessColorBox(color = color.value) {
        color.value = Color.random()
    }
}


