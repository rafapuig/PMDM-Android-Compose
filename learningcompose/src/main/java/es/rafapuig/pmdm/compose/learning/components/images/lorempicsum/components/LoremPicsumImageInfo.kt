package es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.model.ImageInfo
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.util.calculateImageSize
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.util.isLandscape
import es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.util.toDp

@Composable
fun LoremPicsumImageInfo(
    info: ImageInfo,
    modifier: Modifier = Modifier
) {
    val isLandscape = isLandscape()

    var imageMeasured by remember { mutableStateOf(false) }
    var widthPx by remember { mutableStateOf(0) }
    var heightPx by remember { mutableStateOf(0) }

    Box(
        modifier = modifier then
                if (imageMeasured) Modifier
                else Modifier.onGloballyPositioned { layoutCoordinates ->

                    Log.d("AsyncImage", "onGloballyPositioned")

                    calculateImageSize(
                        originalSize = IntSize(
                            width = info.width,
                            height = info.height
                        ),
                        layoutSize = layoutCoordinates.size,
                        isLandscape = isLandscape
                    ).let { (width, height) ->
                        widthPx = width
                        heightPx = height
                    }

                    imageMeasured = true
                }

    ) {
        if (imageMeasured) {
            LoremPicsumImage(
                id = info.id,
                width = widthPx.toDp(),
                height = heightPx.toDp(),
                description = info.author
            )
        }
    }
}
