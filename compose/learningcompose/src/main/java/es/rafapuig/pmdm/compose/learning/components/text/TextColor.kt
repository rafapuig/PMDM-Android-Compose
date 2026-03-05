package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme

/**
 * Muchos de los parámetros del composable Text
 * son atajos para cambiar el estilo del texto
 * uno de ellos es el parámetro color
 */
@Composable
fun TextWithColor(text: String, color: Color) {
    Text(text = text, color = color)
}

/**
 * La siguiente implementación demuestra
 * que el parámetro color de la función composable Text
 * sirve como atajo para aplicar el color al estilo del texto
 */
@Composable
fun TextWithColor2(text: String, color: Color) {
    Text(
        text = text,
        style = LocalTextStyle.current.copy(color = color)
    )
}

@Preview(showBackground = true)
@Composable
fun TextWithColorPreview() {
    PMDMComposeTheme {
        Column {
            CompositionLocalProvider(
                LocalTextStyle provides LocalTextStyle.current.copy(textDecoration = TextDecoration.Underline)
            ) {
                /**
                 * En este scope proporcionamos el valor de LocalTextStyle
                 * con el textDecoration en Underline
                 */

                TextWithColor(text = "Hola Compose", color = Color.Red)
                TextWithColor2(text = "Hola Compose", color = Color.Red)
            }
            /**
             * Fuera del scope el TextStyle es el valor por defecto
             */
            TextWithColor(text = "Hola Compose", color = Color.Red)
            TextWithColor2(text = "Hola Compose", color = Color.Red)

            CompositionLocalProvider(LocalContentColor provides Color.Yellow) {
                Text(text = "Hola Compose")
            }
            Text(text = "Hola Compose") // Color por defecto
        }
    }
}
