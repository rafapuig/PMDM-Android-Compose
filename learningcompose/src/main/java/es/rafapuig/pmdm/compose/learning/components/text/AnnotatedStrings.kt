package es.rafapuig.pmdm.compose.learning.components.text

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * Los string anotados permiten dividir el texto en varias secciones
 * cada una con su propio estilo
 *
 * Un AnnotatedString se crea utilizando la función builder buildAnnotatedString
 * pasándole los textos y los estilos que la componen
 *
 * Hay dos estilos:
 * - SpanStyle (para aplicar a un span de caracteres individuales de un string)
 * - ParagraphStyle (aplica a párrafos)
 */

@Preview(showBackground = true)
@Composable
fun SpanStyleDemo() {

    val header = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Blue)
                )
            )
        ) {
            append("Annotated Strings en Jetpack Compose \uD83D\uDC96")
        }
    }


    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        ) {
            append("E")
        }
        withStyle(SpanStyle(color = Color.Gray)) {
            append("jemplo")
        }
        append(" de ")
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Blue
            )
        ) {
            append("annotated")
        }
        append(" string en ")

        withLink(LinkAnnotation.Url("http://develpoper.android.com/jetpack/compose")) {
            append("Compose")
        }
    }


    val paragraph = buildAnnotatedString {
        append("Esto es texto al que no se la aplica ningún estilo todavía\n")

        withStyle(
            style = ParagraphStyle(
                lineHeight = 30.sp,
                textIndent = TextIndent(
                    firstLine = 50.sp,
                    restLine = 20.sp
                )
            )
        ) {
            append(
                "Este texto ha sido identado en la primera linea 30 sp " +
                        "y en el resto solamente 10 sp. Tambien se le ha " +
                        "icrementado el alto de la linea"
            )
        }
        withStyle(
            ParagraphStyle(
                textAlign = TextAlign.End
            )
        ) {
            append("Este texto está alineado a la derecha")
        }
    }

    Column {
        Text(header)
        Text(text = annotatedString)
        Text(paragraph)
    }
}