package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleColoredWithNumber(
    size: Dp,
    color: Color,
    counter: Int = 9
) {
    Box(
        modifier = Modifier
            .size(size = size)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text("$counter",
            fontSize = 50.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun CircleColoredWithNumberPreview() {
    CircleColoredWithNumber(
        size = 200.dp,
        color = Color.DarkGray
    )
}