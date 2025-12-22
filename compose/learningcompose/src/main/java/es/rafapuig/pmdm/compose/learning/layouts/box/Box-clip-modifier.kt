package es.rafapuig.pmdm.compose.learning.layouts.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun UsingClipModifierWithBoxDemoScreen() {

    val boxModifier = Modifier.size(100.dp)

    val size = 300.dp
    Column(
        modifier = Modifier.height(size),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.width(size),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                boxModifier
                    .background(Color.Red)
                    .clip(RectangleShape)
            )
            Box(
                boxModifier
                    .clip(CircleShape)
                    .background(Color.Yellow)
            )

        }
        Row(
            modifier = Modifier.width(size),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                boxModifier
                    .clip(RoundedCornerShape(25))
                    .background(Color.Green)
            )
            Box(
                boxModifier
                    .clip(CutCornerShape(25))
                    .background(Color.Blue)
            )
        }
    }

}