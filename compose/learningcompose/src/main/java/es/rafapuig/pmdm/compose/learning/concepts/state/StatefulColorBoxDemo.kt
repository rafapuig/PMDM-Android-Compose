package es.rafapuig.pmdm.compose.learning.concepts.state

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
 * Función de extensión de la clase Color para generar un color aleatorio
 * La función se usa como si fuera un miembro estático de la clase Color,
 * ya que estamos usando como receptor el objeto companion de la clase Color
 */
fun Color.Companion.random() =
    with(Random.Default) {
        Color(
            red = nextFloat(),
            green = nextFloat(),
            blue = nextFloat(),
            1f
        )
    }

@Composable
fun ColorBox() {

    /**
     * Estado "color" declarado por el composable ColorBox
     * lo que lo convierte en un stateful composable
     */
    var color by remember {
        mutableStateOf(Color.Yellow)
    }

    Box(
        modifier = Modifier
            .background(color)
            .size(300.dp)
            .clickable { color = Color.random() })
}


@Preview
@Composable
fun ColorBoxPreview() {
    ColorBox()
}