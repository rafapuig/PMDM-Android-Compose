package es.rafapuig.pmdm.compose.learning.layouts.box

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * El organizador (layout) Box apila sus composables hijos uno encima de otro
 *
 * El orden en que los composables hijos son apilados
 * lo determina el orden en que son llamados dentro de la función Box
 * El primero hijo va al fondo de la pila.
 */

@Preview(showBackground = true)
@Composable
fun BoxLayoutDemoScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        val height = 200.dp
        val width = 200.dp
        val cellModifier = Modifier.size(width = width, height = height)
        TextCell(text = "1", cellModifier, Color.Red)
        TextCell(text = "2", cellModifier, Color.Green)
        TextCell(text = "3", cellModifier, Color.Blue)
    }
}

@Composable
fun TextCell(
    text: String,
    modifier: Modifier = Modifier,
    fontColor: Color = Color.Black,
    fontSize: Int = 170
) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 5.dp, color = Color.Black)

    //Surface {
        Text(
            text = text,
            modifier = cellModifier then modifier,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = fontColor
        )
    //}
}