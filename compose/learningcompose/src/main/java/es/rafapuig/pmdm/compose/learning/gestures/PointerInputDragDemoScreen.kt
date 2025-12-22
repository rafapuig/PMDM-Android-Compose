package es.rafapuig.pmdm.compose.learning.gestures

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import es.rafapuig.pmdm.compose.learning.R
import kotlin.math.roundToInt

@Preview
@Composable
fun PointerInputDragDemoScreen(modifier: Modifier = Modifier) {

    var parentSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { size -> parentSize = size }
    ) {
        // Para evitar el flickering de 0,0 inicial
        if (parentSize != IntSize.Zero) {
            DraggableImage(parentSize, Modifier.size(200.dp))
        }
    }
}

@Composable
fun DraggableImage(parentSize: IntSize, modifier: Modifier = Modifier) {

    var offset by remember { mutableStateOf(Offset.Zero) }
    var imageSize by remember { mutableStateOf(Size.Zero) }

    // Cuando se conozcan ambos tamaños centramos
    LaunchedEffect(parentSize, imageSize) {
        if (parentSize == IntSize.Zero || imageSize == Size.Zero) return@LaunchedEffect
        offset = with(parentSize) {
            Offset(width - imageSize.width, height - imageSize.height) / 2f
        }
    }


    //val density = LocalDensity.current
    //LaunchedEffect(parentSize) {
    //if (parentSize == IntSize.Zero) return@LaunchedEffect
    //val (widthPx, heightPx) = with(density) {
    //Size(imageSize.width.toPx(), imageSize.height.toPx())
    //}
    //offset = with(parentSize) {
    //Offset(width - widthPx, (height - heightPx)) / 2f
    //}
    //}

    Image(
        painter = painterResource(id = R.drawable.levante_ud_logo),
        contentDescription = null,
        modifier
            .onGloballyPositioned { layoutCoordinates ->
                imageSize = layoutCoordinates.size.toSize()
            }
            .offset { offset.toIntOffset() }
            //.size(imageSize)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    //change.consume()
                    offset += dragAmount
                }
            },
        contentScale = ContentScale.Fit
    )


}

fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

// Clase para manejar Offset en dp
data class OffsetDp(val x: Dp, val y: Dp)

@Composable
fun Offset.toDp(): OffsetDp {
    val density = LocalDensity.current
    return with(density) {
        OffsetDp(x.toDp(), y.toDp())
    }
}

@Composable
fun OffsetDp.toPx(): Offset {
    val density = LocalDensity.current
    return with(density) {
        Offset(x.toPx(), y.toPx())
    }
}

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}