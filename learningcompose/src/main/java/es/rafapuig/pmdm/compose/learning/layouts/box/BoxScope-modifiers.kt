package es.rafapuig.pmdm.compose.learning.layouts.box.scope.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
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

/**
 * El layout Box proporciona modificadores adicionales
 * para aplicar a los composables hijos
 * son los BoxScope modifiers
 * - align()
 * - matchParentSize()
 */

@Preview(showBackground = true)
@Composable
fun BoxLayoutDemoScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(300.dp)
    ) {
        val cellModifier = Modifier.size(95.dp)
        TextCell("TopStart", cellModifier.align(Alignment.TopStart))
        TextCell("TopCenter", cellModifier.align(Alignment.TopCenter))
        TextCell("TopCenter", cellModifier.align(Alignment.TopEnd))
        TextCell("CenterStart", cellModifier.align(Alignment.CenterStart))
        TextCell("Center", cellModifier.align(Alignment.Center))
        TextCell("CenterEnd", cellModifier.align(Alignment.CenterEnd))
        TextCell("BottomStart", cellModifier.align(Alignment.BottomStart))
        TextCell("BottomCenter", cellModifier.align(Alignment.BottomCenter))
        TextCell("BottomEnd", cellModifier.align(Alignment.BottomEnd))
    }
}

@Composable
fun TextCell(
    text: String,
    modifier: Modifier = Modifier,
    fontColor: Color = Color.Blue,
    fontSize: Int = 14
) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 1.dp, color = Color.Black)
        .then(modifier)
        .wrapContentHeight(align = Alignment.CenterVertically)

    //Surface {
    Text(
        text = text,
        modifier = cellModifier,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = fontColor
    )
    //}
}