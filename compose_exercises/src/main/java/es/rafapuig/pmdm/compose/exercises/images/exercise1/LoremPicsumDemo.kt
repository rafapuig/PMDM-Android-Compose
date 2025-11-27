package es.rafapuig.pmdm.compose.exercises.images.exercise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.rememberAsyncImagePainter

@Preview
@Composable
fun CoilTest() {
    val url = "https://picsum.photos/1200"
    val model = rememberAsyncImagePainter(
        model = url
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = model,
            contentDescription = null,
            modifier = Modifier.clip(
                MaterialTheme.shapes.extraLarge
            )
        )
    }
}