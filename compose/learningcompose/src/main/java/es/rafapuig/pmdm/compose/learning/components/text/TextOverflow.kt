package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextOverflowDemo() {

    Text("Overflow Ellipsis")
    Text(
        "Titular de una noticia muy pero que muy muy largo",
        maxLines = 1, // Hay que limitar el número de lineas del texto para que se produzca el overflow
        overflow = TextOverflow.Ellipsis, // ... al final
    )

    HorizontalDivider()

    Text("Overflow Ellipsis")
    Text(
        "Titular de una noticia muy pero que muy muy largo",
        maxLines = 1,
        overflow = TextOverflow.MiddleEllipsis, // Esto ... final
    )

    HorizontalDivider()

    Text("Overflow StartEllipsis")
    Text(
        "Titular de una noticia muy pero que muy muy largo",
        maxLines = 1,
        overflow = TextOverflow.StartEllipsis, // ... final
    )

    HorizontalDivider()

    Text("Overflow Clip")
    Text(
        "Titular de una noticia muy pero que muy muy largo",
        maxLines = 1,
        overflow = TextOverflow.Clip, // Recorta el texto sobrante que no cabe en el contenedor
        softWrap = false
    )

    HorizontalDivider()
    Text("Overflow Visible") // Debería sobrepasar los limites!!!
    Text(
        "Titular de una noticia muy pero que muy muy largo",
        maxLines = 1,
        overflow = TextOverflow.Visible,
        softWrap = false
    )

}

@Preview(
    showBackground = true,
    widthDp = 400)
@Composable
fun TextOverflowPreview() {
    Column(
        modifier = Modifier
            .requiredWidth(260.dp)
            .background(Color.Cyan)
    ) {
        TextOverflowDemo()
    }
}