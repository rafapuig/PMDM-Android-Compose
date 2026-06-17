package es.rafapuig.pmdm.compose.learning.layout.layouts.rowcolumn

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextCell(
    text: String,
    modifier: Modifier = Modifier, // modifier debe ser el primer parámetro con valor por defecto
    color: Color = Color.Unspecified
) {
    val cellModifier = modifier
        .padding(4.dp) // actua como margen antes del borde
        .border(width = 4.dp, color = Color.Black)
        .size(92.dp) // 4 dp son para el borde (contenido queda en 88 dp)

    Text(
        text = text,
        modifier = (cellModifier then modifier)
            .wrapContentHeight( // centrar el texto verticalmente en su caja
                unbounded = true,
                align = Alignment.CenterVertically
            )
        ,
        color = color,
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun TextCellPreview() {
    TextCell("1")
}

@Preview(showBackground = true)
@Composable
fun TextCellPreview10() {
    TextCell("9")
}