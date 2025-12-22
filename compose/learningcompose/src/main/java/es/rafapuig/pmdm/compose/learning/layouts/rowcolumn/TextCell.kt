package es.rafapuig.pmdm.compose.learning.layouts.rowcolumn

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextCell(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    val cellModifier = modifier
        .padding(4.dp)
        .size(92.dp)
        .border(width = 4.dp, color = Color.Black)

    Text(
        text = text,
        modifier = (cellModifier then modifier).wrapContentHeight(),
        color = color,
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}