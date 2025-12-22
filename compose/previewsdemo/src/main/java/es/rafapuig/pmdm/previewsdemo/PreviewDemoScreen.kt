package es.rafapuig.pmdm.compose.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun PreviewDemoScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MyButton("Hola Compose", 300.dp)
        MyButton("Adios Compose", color = Color.Magenta)
    }
}

@Composable
private fun MyButton(text: String, width: Dp = 200.dp, color: Color = Color.Yellow) {
    Text(
        text,
        Modifier
            .clip(RoundedCornerShape(30))
            .background(color)
            .padding(16.dp)
            .width(width)
            .clickable {},
        textAlign = TextAlign.Center,
        color = Color.Blue
    )
}