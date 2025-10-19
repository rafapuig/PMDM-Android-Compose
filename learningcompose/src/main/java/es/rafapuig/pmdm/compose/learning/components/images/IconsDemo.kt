package es.rafapuig.pmdm.compose.learning.components.images

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.R

/**
 * Para este ejemplo hemos creado un recurso de tipo Vector Asset
 * seleccionado del Clip Art
 *
 * Alli tenemos, iconos, que son imagenes vectoriales en un solo color.
 *
 * Por que un icono se considera una imagen con el cuerpo monocolor y un fondo transparente.
 * Al que luego vamos a TINTAR de un color específico cuando lo usamos en un composable
 * mediante el componente Icon.
 *
 */

 @Preview(showBackground = true)
@Composable
fun IconsDemo() {
    Icon(
        painter = painterResource(id = R.drawable.ic_outline_cast_24),
        contentDescription = "Transmisión",
        tint = MaterialTheme.colorScheme.primary
    )
}