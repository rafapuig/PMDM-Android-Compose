package es.rafapuig.pmdm.compose.learning.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter


val imageURL = "https://raw.githubusercontent.com/rafapuig/" +
        "PMDM-Android-Compose/refs/heads/master/learningcompose/" +
        "src/main/res/drawable/vacation.jpg"

@Preview
@Composable
fun NetworkImageDemo() {
    var message by remember { mutableStateOf("") }

    val painter = rememberAsyncImagePainter(
        model = imageURL
    )

    Image(
        painter = painter,
        contentDescription = "Vacation"
    )

    Column {
        AsyncImage(
            model = imageURL,
            contentDescription = "Vacation",
            onError = {
                it.result.throwable.printStackTrace()
            }
        )
    }

}
