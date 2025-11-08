package es.rafapuig.pmdm.compose.learning.components.images.lorempicsum

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.components.LoremPicsumImageInfo
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.data.LoremPicsumApi.fetchImageInfo
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.model.ImageInfo
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.model.aspectRatio
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.util.isLandscape
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme

@Composable
fun LoremPicsum(
    id: Int,
    modifier: Modifier = Modifier,
    onInfo: (ImageInfo) -> Unit = {}
) {
    var info: ImageInfo? by remember { mutableStateOf(null) }

    LaunchedEffect(id) {
        info = fetchImageInfo(id)
        Log.d("AsyncImage", "LaunchedEffect info = $info")
        onInfo(info!!)
    }

    info?.let { imageInfo ->
        LoremPicsumImageInfo(
            info = imageInfo,
            modifier = modifier.aspectRatio(imageInfo.aspectRatio)
        )
    } ?: CircularProgressIndicator()

}


@Composable
fun LoremPicsumScaffoldPreview(
    content: @Composable (id: Int, onInfo: (ImageInfo) -> Unit) -> Unit
) {

    PMDMComposeTheme {
        var info: ImageInfo? by remember { mutableStateOf(null) }

        Scaffold { innerPadding ->
            Surface(
                modifier =
                    if (!isLandscape()) Modifier.padding(innerPadding) else Modifier
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    content(236) { info = it }
                    if (!isLandscape()) {
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = info?.author ?: "",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        val uriHandler = LocalUriHandler.current
                        Text(
                            text = info?.url ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    uriHandler.openUri(info?.url?:"")
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoremPicsumFixedHeightPreview() {
    LoremPicsumScaffoldPreview { id, onInfo ->
        LoremPicsum(
            id = id,
            modifier = Modifier.height(200.dp)
        ) {
            onInfo(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoremPicsumFixedWidthPreview() {
    LoremPicsumScaffoldPreview { id, onInfo ->
        LoremPicsum(
            id = id,
            modifier = Modifier.width(200.dp)
        ) {
            onInfo(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoremPicsumMaxSizePreview() {
    LoremPicsumScaffoldPreview { id, onInfo ->
        LoremPicsum(
            id = id,
            modifier =
                if (isLandscape()) Modifier.fillMaxHeight()
                else Modifier.fillMaxWidth(),
            onInfo = onInfo
        )
    }
}