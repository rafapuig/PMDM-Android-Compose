package es.rafapuig.pmdm.compose.learning.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.R
import es.rafapuig.pmdm.compose.learning.layout.modifiers.ModifiersDemoScreen

/**
 * El proceso de layout (ubicación) de un elemento de UI
 * https://developer.android.com/develop/ui/compose/layouts/basics#model
 *
 * Compose transforma estado de UI en elementos de UI en tres etapas:
 * 1. Composición
 * 2. Layout (ubicación) <------
 * 3. Renderización
 *
 * https://developer.android.com/static/develop/ui/compose/images/composition-layout-drawing.svg
 *
 * Fundamentals of Compose Layouts and Modifiers
 * https://www.youtube.com/watch?v=xc8nAcVvpxY
 */

/**
 * Si un composable emite varios elementos de UI
 * ¿Cómo los ubicamos / organizamos?
 */

/**
 * Si no proporcionamos una guía para la organización (layout),
 * Compose coloca los elementos de UI uno encima del otro, apilados
 */
@Preview(showBackground = true)
@Composable
fun PersonCard() {
    Text("Perico Palotes")
    Text("35 años")
}

/**
 * Para organizar los elementos de UI emitidos por un composable
 * Compose proporciona un conjunto de layouts estándar
 * - Box, Row, Column y BoxWithConstraints
 * https://developer.android.com/static/develop/ui/compose/images/layout-column-row-box.svg
 *
 * Y modificadores para layouts:
 * - aspectRatio, offset, size, fillMaxSize, fillMaxWidth, fillMaxHeight, padding, etc.
 *
 * https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary
 *
 * (además, permite crear nuestros propios layouts personalizados)
 */

/** Column layout **/
@Preview(showBackground = true)
@Composable
fun PersonCardWithColumnLayout() {
    // Organizador de elementos en columna
    Column {
        Text("Perico Palotes")  // Primer elemento de la columna
        Text("35 años")         // Segundo elemento de la columna
    }
}

/** Row layout **/
@Preview(showBackground = true)
@Composable
fun PersonCardWithRowLayout() {
    // Organizador de elementos en fila
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Primer elemento de la fila
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.retrato_de_hombre_blanco_aislado),
            contentDescription = "Portrait",
            contentScale = ContentScale.Crop
        )

        // Segundo elemento de la fila
        Column {
            Text("Perico Palotes")
            Text("35 años")
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PersonAvatar() {
    // Organizador de elementos en Caja (apilados)
    Box(contentAlignment = Alignment.BottomEnd) {

        // Primer elemento (irá detrás)
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.retrato_de_hombre_blanco_aislado),
            contentDescription = "Portrait",
            contentScale = ContentScale.Crop
        )

        // Segundo elemento (irá delante / encima del primero)
        Icon(
            modifier = Modifier
                .size(16.dp)
                .border(1.dp, Color.White, CircleShape)
                .clip(CircleShape)
                .background(Color.Blue)
                .padding(2.dp)
            ,imageVector = Icons.Filled.Check,
            tint = Color.White,
            contentDescription = "Check mark")
    }

}

@Preview(showBackground = true)
@Composable
fun PersonCardWithAvatar() {
    Row(
        verticalAlignment = Alignment.CenterVertically, // Sobre el eje horizontal -----
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Primer elemento de la fila
        PersonAvatar()

        // Segundo elemento de la fila
        Column {
            Text("Perico Palotes", fontWeight = FontWeight.Bold)
            Text("35 años", fontSize = 10.sp)
        }
    }
}
