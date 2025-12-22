package es.rafapuig.pmdm.previewsdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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

@Composable
fun Tag(
    text: String,
    width: Dp? = null,
    color: Color = Color.Yellow
) {
    Text(
        text,
        Modifier
            .clip(RoundedCornerShape(30))
            .background(color)
            .padding(10.dp)
            then if (width != null) Modifier.width(width) else Modifier,
        textAlign = TextAlign.Center,
        color = Color.Blue
    )
}

@Preview
@Composable
fun MyButtonPreview() {
    Tag("Hola Compose")
}


data class Tag(
    val name: String,
    val color: Color
)

val tags = listOf(
    Tag("Acción", Color.Red),
    Tag("Aventura", Color.LightGray),
    Tag("Drama", Color.Green),
    Tag("Comedia", Color.Yellow),
    Tag("Terror", Color.Magenta),
    Tag("Ciencia Ficción", Color.Cyan),
    Tag("Fantasía", Color.Gray),
    Tag("Misterio", Color.Red),
    Tag("Suspense", Color.DarkGray),
    Tag("Animación", Color.LightGray),
    Tag("Musical", Color.Green),
    Tag("Romántica", Color.Yellow),
    Tag("Western", Color.Magenta),
)


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagList(tags: List<Tag>) {
    FlowRow {
        tags.forEach { tag ->
            Tag(
                text = tag.name,
                color = tag.color
            )
        }
    }
}

@Preview
@Composable
fun TagListScreen(modifier: Modifier = Modifier) {
    TagList(tags = tags)
}