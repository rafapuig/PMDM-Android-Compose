package es.rafapuig.pmdm.compose.learning.concepts.state.hoisting2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
 * En este ejemplo tenenos dos ColorBox
 * Cuando en el primero se hace click se cambia el valor de estado color
 * El segundo se actualiza al valor del color
 */
@Preview(showBackground = true)
@Composable
fun ColorBoxHoistingDemo2Screen(modifier: Modifier = Modifier) {
    /**
     * El estado lo hemos hoisteado hacia el composable padre
     */
    var color by remember {
        mutableStateOf(Color.random())
    }

    /**
     * El manejador de eventos onColorChange accede al estado color y lo actualiza
     * (actualizar el estado color provocará que haya una recomposición del composable ColorBox)
     */
    val onColorChange: (Color) -> Unit = {
        color = it
    }

    Column {
        ColorBox { onColorChange(it) } // Cuando se clica sobre de este ColorBox se cambia el color
        ColorBox(color) // Este componente se actualiza al color aleatorio
    }

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
fun ColorBox(color: Color = Color.White, onColorChange: ((Color) -> Unit)? = null) {
    Box(
        modifier = Modifier
            .background(color)
            .size(300.dp)
                then if (onColorChange != null)
            Modifier.clickable { onColorChange(Color.random()) } else Modifier
    )
}