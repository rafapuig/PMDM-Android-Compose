package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

@Composable
fun DiceFace(
    value: Int,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .aspectRatio(1f)
    ) {

        val color = Color.Black

        drawRoundRect(
            Color.White,
            cornerRadius = CornerRadius(size.minDimension / 16)
        )

        drawRoundRect(
            color,
            cornerRadius = CornerRadius(size.minDimension / 16),
            style = Stroke(
                width = 16f
            )
        )

        val size = this.size
        val radius = size.minDimension / 11

        if (value in listOf(2, 3, 4, 5, 6))
            drawCircle(
                color = color, radius = radius,
                center = Offset(size.center.x * 0.5f, size.center.y * 0.5f)
            )


        if (value in listOf(4, 5, 6))
            drawCircle(
                color = color, radius = radius,
                center = Offset(size.center.x * 1.5f, size.center.y * 0.5f)
            )

        if (value == 6) {
            drawCircle(
                color = color, radius = radius,
                center = Offset(size.center.x * 1.5f, size.center.y * 1.0f)
            )

            drawCircle(
                color = color, radius = radius,
                center = Offset(size.center.x * 0.5f, size.center.y * 1.0f)
            )
        }

        if (value in listOf(4,5, 6))
            drawCircle(
                color = color, radius = radius,
                center = Offset(size.center.x * 0.5f, size.center.y * 1.5f)
            )

        if (value in listOf(2, 3, 4, 5, 6))
            drawCircle(
                color = color, radius = radius,
                center = Offset(size.center.x * 1.5f, size.center.y * 1.5f)
            )

        if (value in listOf(1, 3, 5))
            drawCircle(color = color, radius = radius)

    }
}


class FaceProvider : PreviewParameterProvider<Int> {
    override val values: Sequence<Int>
        get() = sequenceOf(1, 2, 3, 4, 5, 6)
}


@Preview(showBackground = false)
@Composable
fun DiceFacePreview(
    @PreviewParameter(FaceProvider::class)
    face: Int
) {
    Box(modifier = Modifier.width(200.dp)) {
        DiceFace(value = face)
    }
}