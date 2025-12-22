package es.rafapuig.pmdm.compose.learning.components.images.lorempicsum.util

import android.util.Log
import androidx.compose.ui.unit.IntSize


fun calculateAspectRatio(width: Int, height: Int): Float {
    return width.toFloat() / height.toFloat()
}

val IntSize.aspectRatio get() = calculateAspectRatio(width, height)


fun calculateImageSize(
    originalSize: IntSize,
    layoutSize: IntSize,
    isLandscape: Boolean
): IntSize {

    val aspectRatio =  originalSize.aspectRatio

    Log.i("Rafa", "calculateImageSize: $aspectRatio")

    Log.i("Rafa", "calculateImageSize: $layoutSize")

    val width =
        if (!isLandscape) layoutSize.width
        else (layoutSize.height * aspectRatio).toInt()

    val height =
        if (isLandscape) layoutSize.height
        else (layoutSize.width / aspectRatio).toInt()

    Log.i("Rafa", "calculateImageSize: $width, $height")


    return IntSize(width, height)

}