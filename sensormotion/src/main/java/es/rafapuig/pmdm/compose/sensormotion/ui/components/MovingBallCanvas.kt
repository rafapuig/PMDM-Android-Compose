package es.rafapuig.pmdm.compose.sensormotion.ui.components

import android.util.Log
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

private val TAG = "MovingBallCanvas"

@Composable
fun MovingBallCanvas(
    normalized: Pair<Float, Float>, //Coordenadas x, y normalizadas entre -1 y 1
    ballRadius: Dp
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        /**
         * El receptor implícito de la lambda argumento de BoxWithConstraints
         * es un objeto de tipo BoxWithConstraintsScope
         * Nos da varias propiedades para obtener el ancho y alto del Canvas
         * Tanto en Dp: maxWidth, maxHeight
         * como en Px: constraints.maxWidth, constraints.maxHeight
         */
        Log.i(TAG, "Max Width en Dd = ${this.maxWidth}")
        Log.i(TAG, "Max Height en Dd = ${this.maxHeight}")
        Log.i(TAG, "Max Width en Px = ${constraints.maxWidth}")
        Log.i(TAG, "Max Height en Px = ${constraints.maxHeight}")
        /**
         * La función toPx() es un método de la clase Density
         * Esta definida como función de extension de la clase Dp
         * Por eso necesitamos llamarla en un ámbito que combine
         * ambos receptores: Dp y Density         *
         * - uno que refiere al objeto Density por que es toPx es miembro instancia
         * - otro que refiere al receptor Dp de la función extendida)
         *
         * Eso solamente lo podemos hacer con with(/*Density*/) {}
         */
        val radiusPx = with(LocalDensity.current) {
            ballRadius.toPx()
        }

        /**
         * Calcular el ancho y alto del Componente en píxeles
         */
        val widthPx = with(LocalDensity.current) {
            this@BoxWithConstraints.maxWidth.toPx()
        }

        val heightPx = this.constraints.maxHeight.toFloat()

        /**
         * Calcular el centro del canvas en píxeles
         */
        val centerX = widthPx / 2f
        val centerY = heightPx / 2f

        /**
         * Calcular el desplazamiento máximo en píxeles
         * para mover la bola desde el centro de la pantalla
         */
        /** La mitad de la anchura del canvas menos el radio menos un padding de 8px */
        val maxDx = (widthPx / 2f) - radiusPx - 8f
        val maxDy = (heightPx / 2f) - radiusPx - 8f

        /**
         * Calcular la posición de la bola en píxeles
         */
        val (coordX, coordY) = normalized
        /** Equivale a:
        val coordX = normalized.first
        val coordY = normalized.second
        */

        /** Desde el centro del canvas un % indicado por coordX y coordY */
        val ballX = centerX + (maxDx * coordX)
        val ballY = centerY + (maxDy * -coordY)

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            // fondo
            drawRect(
                color = Color(0xFF202020),
                size = size
            )
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
                center = Offset(ballX, ballY)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MovingBallCanvasPreview() {
    MovingBallCanvas(
        Pair(0f, 0f),
        24.dp
    )
}