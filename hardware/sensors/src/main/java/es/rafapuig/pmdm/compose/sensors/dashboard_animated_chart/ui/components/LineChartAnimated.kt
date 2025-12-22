package es.rafapuig.pmdm.compose.sensors.ui.components


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData.series1
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData.series2
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData.series3
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

@Composable
fun LineChartAnimated(
    series1: List<Float>,
    series2: List<Float>,
    series3: List<Float>,
    labels: List<String>,
    colors: List<Color>,
    historySize: Int = HISTORY_SIZE
) {

    val animatedSeries1 =
        remember { List(historySize) { Animatable(0f) } }

    val animatedSeries2 =
        remember { List(historySize) { Animatable(0f) } }

    val animatedSeries3 =
        remember { List(historySize) { Animatable(0f) } }

    val scope = rememberCoroutineScope()

    // Animar suavemente los valores
    LaunchedEffect(series1, series2, series3) {
        series1.forEachIndexed { idx, value ->
            if (idx < animatedSeries1.size) {
                scope.launch { animatedSeries1[idx].animateTo(value, tween(100)) }
            }
        }
        series2.forEachIndexed { idx, value ->
            if (idx < animatedSeries2.size) {
                scope.launch { animatedSeries2[idx].animateTo(value, tween(100)) }
            }
        }
        series3.forEachIndexed { idx, value ->
            if (idx < animatedSeries3.size) {
                scope.launch { animatedSeries3[idx].animateTo(value, tween(100)) }
            }
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        val width = size.width
        val height = size.height

        val allValues =
            animatedSeries1.map { it.value } + animatedSeries2.map { it.value } + animatedSeries3.map { it.value }
        if (allValues.isEmpty()) return@Canvas

        val minVal = allValues.minOrNull() ?: 0f
        val maxVal = allValues.maxOrNull() ?: 1f
        val range = max(0.01f, maxVal - minVal)

        fun Float.normalize() = (this - minVal) / range * height

        fun drawSeries(data: List<Animatable<Float, *>>, color: Color) {
            val points = data.mapIndexed { idx, anim ->
                val x = idx * width / (historySize - 1)
                val y = height - anim.value.normalize()
                androidx.compose.ui.geometry.Offset(x, y)
            }
            for (i in 0 until points.size - 1) {
                drawLine(color, points[i], points[i + 1], strokeWidth = 3f)
            }
        }

        // Dibujar grid
        val gridLines = 5
        for (i in 0..gridLines) {
            val y = i * height / gridLines
            drawLine(
                Color.LightGray,
                Offset(0f, y),
                Offset(width, y),
                strokeWidth = 2f
            )
        }

        // Dibujar series animadas
        drawSeries(animatedSeries1, colors.getOrElse(0) { Color.Cyan })
        drawSeries(animatedSeries2, colors.getOrElse(1) { Color.Magenta })
        drawSeries(animatedSeries3, colors.getOrElse(2) { Color.Yellow })

        // Leyenda simple
        labels.forEachIndexed { idx, text ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    text,
                    60f + idx * 60f,
                    60f,
                    android.graphics.Paint()
                        .apply { color = android.graphics.Color.WHITE; textSize = 52f })
            }
        }
    }
}

@Preview
@Composable
fun LineChartAnimatedPreview() {
    LineChartAnimated(
        series1.take(HISTORY_SIZE).toList(),
        series2.take(HISTORY_SIZE).toList(),
        series3.take(HISTORY_SIZE).toList(),
        labels = listOf("X", "Y", "Z"),
        colors = listOf(Color.Cyan, Color.Magenta, Color.Yellow)
    )
}
