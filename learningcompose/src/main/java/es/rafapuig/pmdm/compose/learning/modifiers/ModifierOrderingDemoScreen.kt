package es.rafapuig.pmdm.compose.learning.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * El orden en el que se encadenan los modificadores es importante.
 *
 * Cuando se aplica primero el borde seguido del padding
 * el resultado es que el borde aparece por fuera del padding.
 *
 * Para que el borde aparezca dentro del padding, se debe aplicar
 * el padding primero.
 */
@Composable
fun ModifierOrderingDemoScreen(modifier: Modifier = Modifier) {
    /**
     * Podemos usar el parámetro modifier para la expresión de inicialización
     * de la variable local del modifier.
     * Desde ese momento la variable local ensombrece al parámetro de entrada.
     */
    val modifier = modifier
        .padding(10.dp)
        .border(width = 2.dp, color = Color.Blue)

    Text(
        "Hola Modifiers",
        modifier = modifier, // Se usa el modificador de la variable local
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
fun ModifierOrderingDemoScreenPreview() {
    ModifierOrderingDemoScreen()
}