package es.rafapuig.pmdm.compose.learning.components.images

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.rounded.Cast
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

/**
 * Hay una forma más sencilla de usar los iconos de
 * la biblioteca estandar de Material 3
 *
 * Hay que añadir la dependencia en el build.gradle.kts:
 * implementation("androidx.compose.material:material-icons-extended:1.7.3")
 *
 * material-icons-extended
 * contiene todos los íconos de Material Design (filled, outlined, rounded, etc.).
 * Si quieres ahorrar tamaño en la app, puedes incluir solo el set básico (material-icons-core).
 */

@Preview(showBackground = true)
@Composable
fun Material3ExtenderIconsDemo() {
    Icon(
        imageVector = Icons.Filled.Cast,
        contentDescription = "Transmisión",
        tint = MaterialTheme.colorScheme.primary
    )
}