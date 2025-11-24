package es.rafapuig.pmdm.compose.sensormotion.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MovingBallCanvas(
    normalized: Pair<Float, Float>,
    ballRadius: Dp) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val widthPx = with(LocalDensity.current) { maxWidth.toPx() }
        val heightPx = with(LocalDensity.current) { maxHeight.toPx() }
        //constraints.maxHeight.toFloat()

        val radiusPx = with(LocalDensity.current) { ballRadius.toPx() }

        val gx = normalized.first
        val gy = normalized.second

        val centerX = widthPx / 2f
        val centerY = heightPx / 2f

        // Desplazamiento maximo en pixeles para mover la bola desde el centro de la pantalla
        val maxDx = (widthPx / 2f) - radiusPx - 8f
        val maxDy = (heightPx / 2f) - radiusPx - 8f

        val ballX = centerX + (maxDx * gx)
        val ballY = centerY + (maxDy * -gy)

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            // fondo
            drawRect(color = Color(0xFF202020), size = size)
            // Borde guia
            drawCircle(
                color = Color(0xFF505050),
                radius = minOf(maxDx, maxDy),
                center = Offset(centerX, centerY),
                alpha = 0.3f
            )
            // bola
            drawCircle(
                color = Color(0xFF1E88E5),
                radius = radiusPx,
                center = Offset(ballX, ballY))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MovingBallCanvasPreview() {
    MovingBallCanvas(Pair(-0.5f, 0.25f), 24.dp)
}