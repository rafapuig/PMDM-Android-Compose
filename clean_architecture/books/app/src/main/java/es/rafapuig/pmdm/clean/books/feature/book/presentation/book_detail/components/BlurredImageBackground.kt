package es.rafapuig.pmdm.clean.books.feature.book.presentation.book_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import es.rafapuig.pmdm.clean.books.ui.theme.DarkBlue

@Composable
fun BlurredImageBackground(
    imageUrl: String? = null,
    isFavorite: Boolean = false,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    content: @Composable () -> Unit
    ) {
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }

    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onSuccess = {
            val size = it.painter.intrinsicSize
            imageLoadResult =
                if (size.width > 0 && size.height > 0)
                    Result.success(it.painter)
                else
                    Result.failure(Exception("Invalid image dimensions"))
        }
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.3f)
                .background(DarkBlue)
        ) {
            imageLoadResult?.getOrNull()?.let { painter ->
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(20.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.7f)
        ) {

        }
    }

}


@Preview(showBackground = true)
@Composable
fun BlurredImageBackgroundPreview() {
    BlurredImageBackground {}
}