package es.rafapuig.pmdm.compose.sensors.dashboard_chart.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.sensors.core.HISTORY_SIZE
import es.rafapuig.pmdm.compose.sensors.core.domain.SensorsSampleData
import kotlin.math.max


@Composable
fun LineChart(
    series1: List<Float>,
    series2: List<Float>,
    series3: List<Float>,
    historySize: Int = HISTORY_SIZE
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        val width = size.width
        val height = size.height
        val n = maxOf(series1.size, series2.size, series3.size)
        if (n < 2) return@Canvas

        // Determinar min y max para normalización
        val allValues = series1 + series2 + series3
        val minVal = allValues.minOrNull() ?: 0f
        val maxVal = allValues.maxOrNull() ?: 1f
        val range = max(0.01f, maxVal - minVal)

        fun Float.normalize() = (this - minVal) / range * height

        fun drawSeries(data: List<Float>, color: Color) {
            val points = data.mapIndexed { idx, value ->
                val x = idx * width / (historySize - 1)
                val y = height - value.normalize()
                Offset(x, y)
            }
            for (i in 0 until points.size - 1) {
                drawLine(
                    color,
                    points[i],
                    points[i + 1],
                    strokeWidth = 3f
                )
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

        drawSeries(series1, Color.Cyan)
        drawSeries(series2, Color.Magenta)
        drawSeries(series3, Color.Yellow)

        // Ejes horizontal y vertical
        drawLine(
            Color.LightGray,
            Offset(0f, height),
            Offset(width, height),
            strokeWidth = 2f
        )
        drawLine(
            Color.LightGray,
            Offset(0f, 0f),
            Offset(0f, height),
            strokeWidth = 2f
        )
    }
}

@Preview
@Composable
fun LineChartPreview() {
    with(SensorsSampleData) {
        LineChart(
            series1.take(HISTORY_SIZE).toList(),
            series2.take(HISTORY_SIZE).toList(),
            series3.take(HISTORY_SIZE).toList()
        )
    }
}
