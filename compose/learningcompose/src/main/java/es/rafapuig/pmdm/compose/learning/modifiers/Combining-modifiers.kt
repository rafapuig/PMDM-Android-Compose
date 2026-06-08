package es.rafapuig.pmdm.compose.learning.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * COMBINANDO MODIFICADORES
 *
 * Cuando tenemos dos o más modificadores y queremos aplicar los
 * efectos que producen todos ellos
 * a un composable tenemos que combinarlos en un solo modificador
 * y aplicar este último al composable
 *
 * Para combinar modificadores se usa la función infija then
 *
 * Sintaxis:
 *
 * val combinedModifier = modifier1 then modifier2 then modifier3
 *
 */

@Preview
@Composable
fun CombiningModifiersDemo() {

    val modifier1 = Modifier.border(width = 5.dp, color = Color.Blue)
    val modifier2 = Modifier.padding(20.dp)
    val modifier3 = Modifier.height(150.dp)

    /**
     * Combinamos los modificadores en uno solo
     * mediante la función infija then
     */
    val modifier = modifier1.then(modifier2).then(modifier3)

    /**
     * Como la función then es infija podemos usar una sintaxis de operador infijo
     * sin los paréntesis de llamada y el punto
     */

    val myModifier = modifier1 then modifier2 then modifier3


    Text(
        text = "Hola Combinación de Modificadores!!",
        modifier = modifier1 then modifier2 then modifier3,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Cyan
    )
}