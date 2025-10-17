package es.rafapuig.pmdm.compose.learning.concepts.state.hoisting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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

/**
 * En este ejemplo el estado color lo subimos (hoisting) al padre
 *
 * Creamos un objeto función que modifique el valor de estado color
 * y pasamos al composable hijo ColorBox
 * tanto el valor actual del estado del color
 * como el manejador de eventos que se debe llamar cuando cambie el color
 * (el objeto función)
 */
@Preview(showBackground = true)
@Composable
fun ColorBoxHoistingDemoScreen(modifier: Modifier = Modifier) {
    /**
     * El estado lo hemos hoisteado hacia el composable padre
     */
    var color by remember {
        mutableStateOf(Color.Yellow)
    }

    /**
     * El manejador de eventos onColorChange accede al estado color y lo actualiza
     * (actualizar el estado color provocara que haya una recomposición del composable ColorBox)
     */
    val onColorChange: (Color) -> Unit = {
        color = it
    }

    /**
     * Pasamos al composable hijo el valor actual del estado color
     * y el manejador de eventos onColorChange (que tiene acceso al estado color
     * y puede modificarlo)
     */
    ColorBox(color, onColorChange)
}

fun Color.Companion.random() = with(Random.Default) {
    Color(
        red = nextFloat(),
        green = nextFloat(),
        blue = nextFloat(),
        1f
    )
}

/**
 * El composable ColorBox ahora es stateless
 * Recibe un color desde el llamador
 * y un manejador de eventos que se debe llamar cuando cambie el color  *
 */
@Composable
fun ColorBox(color: Color, onColorChange: (Color) -> Unit) {
    Box(
        modifier = Modifier
            .background(color)
            .size(300.dp)
            .clickable { onColorChange(Color.random()) })
}