package es.rafapuig.pmdm.compose.learning.layouts.flow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class ItemProperties(
    val color: Color,
    val width: Dp,
    val height: Dp
) {
    companion object
}

fun ItemProperties.Companion.random() = ItemProperties(
    width = (20..100).random().dp,
    height = (10..40).random().dp,
    color = Color.random()
)

fun Color.Companion.random() = Color(
    red = (0..255).random(),
    green = (0..255).random(),
    blue = (0..255).random(),
    255
)

@Preview(showBackground = true)
@Composable
fun FlowRowLayoutDemoScreen(modifier: Modifier = Modifier) {

    val items = List(12) { ItemProperties.random() }

    FlowRow(
        modifier = modifier.width(300.dp),
        horizontalArrangement = Arrangement.SpaceEvenly, //.spacedBy(-20.dp)
        itemVerticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { properties ->
            Box(
                modifier = Modifier
                    //.align(Alignment.Bottom)
                    .padding(2.dp)
                    .width(properties.width)
                    .height(properties.height) //30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(properties.color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlowColumnLayoutDemoScreen(modifier: Modifier = Modifier) {
    val items = List(24) { ItemProperties.random() }

    FlowColumn(
        modifier = Modifier
            .width(300.dp)
            .height(120.dp),
        verticalArrangement = Arrangement.Top, // Experimenta
        horizontalArrangement = Arrangement.SpaceEvenly,
        itemHorizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.forEach { properties ->
            Box(
                modifier = Modifier
                    //.align(Alignment.CenterHorizontally) //Experimenta
                    .padding(2.dp)
                    .width( properties.width)
                    .height(properties.height)
                    .clip(RoundedCornerShape(8.dp))
                    .background(properties.color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlowColumnLayoutWithWeightDemoScreen(modifier: Modifier = Modifier) {
    val items = List(24) { ItemProperties.random() }

    FlowColumn(
        modifier = Modifier
            .width(300.dp)
            .height(120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        //itemHorizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.forEachIndexed { index, properties ->
            val weight = when {
                index % 2 == 0 -> 2f
                index % 3 == 0 -> 3f
                else -> 0.5f
            }
            Box(
                modifier = Modifier
                    .weight(weight)
                    .padding(2.dp)
                    .width( 30.dp)
                    .height(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(properties.color)
            )
        }
    }
}