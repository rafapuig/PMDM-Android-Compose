package es.rafapuig.pmdm.compose.learning.layouts.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun LayoutModifierDemoScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(120.dp, 80.dp)) {
        ColorBox(Modifier
            .exampleLayout(90,50)
            .background(Color.Blue)
        )
    }
}

@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.padding(1.dp).size(50.dp,10.dp) then modifier)
}

fun Modifier.exampleLayout(
    x: Int,
    y: Int,
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x, y)
    }
}

