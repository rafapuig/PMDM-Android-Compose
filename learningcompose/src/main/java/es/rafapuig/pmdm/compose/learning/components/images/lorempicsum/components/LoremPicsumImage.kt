package es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.LoremPicsum
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.data.LoremPicsumApi
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.model.ImageInfo
import es.rafapuig.pmdm.compose.learning.gestures.toPx
import kotlinx.coroutines.runBlocking

@Composable
fun LoremPicsumImage(
    id: Int,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    description: String? = null,
    contentScale: ContentScale = ContentScale.None
) {
    val widthPx = width.toPx().toInt()
    val heightPx = height.toPx().toInt()

    AsyncImage(
        modifier = modifier,
        model =
            ImageRequest.Builder(LocalContext.current)
                .data(data = "https://picsum.photos/id/$id/$widthPx/$heightPx")
                .size(widthPx, heightPx)
                .build(),
        contentDescription = description,
        contentScale = contentScale,

        onSuccess = {
            Log.d("AsyncImage", "OnSuccess")
            Log.d("AsyncImage", it.result.image.width.toString())
            Log.d("AsyncImage", it.result.image.height.toString())
            //imageLoaded = true
        },
        onLoading = {
            Log.d("AsyncImage", "OnLoading")
            Log.d("AsyncImage", "widthPx = $widthPx")
            Log.d("AsyncImage", "heightPx = $heightPx")
        },
        onError = {
            Log.d("AsyncImage", "OnError")
            Log.d("AsyncImage", it.result.throwable.stackTraceToString())
        }
    )
}

@Preview
@Composable
fun LoremPicsumImagePreview() {
    LoremPicsumImage(
        237,
        300.dp,
        300.dp,
        Modifier.clip(CircleShape),
        "Lorem Picsum Image"
    )
}

fun <T> suspendPreview(block: suspend () -> T): T =
    runBlocking { block() }


class LoremPicsumImagePreviewProvider : PreviewParameterProvider<ImageInfo> {
    override val values: Sequence<ImageInfo>
        get() = suspendPreview {
            LoremPicsumApi.fetchImageListInfo(
                page = 10,
                limit = 5
            )
        }.take(3).asSequence()
}

@Preview
@Composable
fun LoremPicsumImagePreview2(
    @PreviewParameter(LoremPicsumImagePreviewProvider::class) imageinfo: ImageInfo) {
    Scaffold { innerPadding ->
        LoremPicsumImage(
            imageinfo.id,
            300.dp,
            300.dp,
            Modifier.padding(innerPadding).clip(CircleShape),
            imageinfo.url
        )
    }
}