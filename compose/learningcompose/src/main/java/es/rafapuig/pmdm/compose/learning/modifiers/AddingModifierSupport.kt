package es.rafapuig.pmdm.compose.learning.modifiers

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.R


/**
 * Añadir soporte a la modificación en un composable
 *
 * https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-api-guidelines.md#elements-accept-and-respect-a-modifier-parameter
 */

/**
 * Cuando desarrollamos nuestros propios composables
 * tenemos que considerar si incluimos soporte para modificaciones
 * para que el comportamiento final del composable sea más configurable por
 * el usuario del composable.
 *
 * La primera regla a cumplir es que
 * el nombre del parameter debe llamarse modifier
 * y debe ser el primer parámetro opcional (con valor por defecto)
 * en la lista de parámetros.
 */

/**
 * Esta función composable declara dos parámetros
 * - imageResId es un Int para pasar un id de recurso de imagen
 * - modifier es un modificador opcional para personalizar el composable
 * (al ser opcional se puede llamar a la funcion sin pasar ningún modificador)
 * por eso se especifica el modificador vacío como un modificador por defecto
 * para el valor del parámetro modifier
 */
@Composable
fun CustomImage(imageResId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = modifier // Aplicamos el modificador a la imagen
    )
}


@Preview(showBackground = true)
@Composable
fun AddingModifierSupportDemo(modifier: Modifier = Modifier) {

    val modifier = modifier
        .border(width = 2.dp, color = Color.Black)
        .padding(all = 10.dp)

    CustomImage(
        imageResId = R.drawable.vacation,
        modifier = Modifier
            .padding(16.dp)
            .width(270.dp)
            .clip(shape = RoundedCornerShape(30.dp))
    )
}




