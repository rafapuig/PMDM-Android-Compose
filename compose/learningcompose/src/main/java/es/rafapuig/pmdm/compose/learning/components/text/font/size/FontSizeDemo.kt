package es.rafapuig.pmdm.compose.learning.components.text.font.size

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme

/**
 * Si no se usa un tema no está garantizado el tamaño
 * del texto por defecto
 */

@Composable
fun FontSizeTextDemo() {

    Column {

        /**
         * Dentro de Material Theme
         * este provee con
         * ProvideTextStyle(MaterialTheme.typography.bodyLarge)
         *
         * Comprueba la diferencia entre llamar a esta función
         * dentro de una llamada al MaterialTheme (previa 2)
         * y directamente (previa 1).
         */
        val localFontSize = LocalTextStyle.current.fontSize.value
        Text("(1) Font size $localFontSize sp", fontSize = localFontSize.sp)

        /**
         * Si hemos llamado dentro del MaterialTheme
         * entonces el tema es PMDMComoseTheme (el de nuestra app)
         * si no, MaterialTheme se refiere al tema por defecto de Material 3,
         * que tiene tamaño 16 sp
         */
        /**
         * La fuente final es una mezcla entre el tema y el estilo local
         */
        val fontSize = LocalTextStyle.current
            .merge(MaterialTheme.typography.bodyLarge).fontSize.value

        Text("(2) Font size $fontSize sp", fontSize = fontSize.sp)

        /**
         * La fuente por defecto, siempre es la misma
         * no usa información de ningún tema
         */
        val defaultFontSize = TextStyle.Default.fontSize.value
        Text("(3) Font size $defaultFontSize sp", fontSize = defaultFontSize.sp)

        /**
         * Tema de material 3
         */
        val materialFontSize = MaterialTheme.typography.bodyLarge.fontSize.value
        Text("(4) Font size $materialFontSize sp", fontSize = materialFontSize.sp)

        /**
         * El composable Text usa por defecto la mezcla
         * entre el tema y el estilo local
         */
        Text("(5) Font size 16 sp")
    }
}


@Preview(showBackground = true)
@Composable
fun FontSizeTextPreview() {
    FontSizeTextDemo()
}

@Preview(showBackground = true)
@Composable
fun FontSizeTextThemePreview() {
    PMDMComposeTheme {
        FontSizeTextDemo()
    }
}