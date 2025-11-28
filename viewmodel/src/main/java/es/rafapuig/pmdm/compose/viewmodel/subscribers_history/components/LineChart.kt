package es.rafapuig.pmdm.compose.viewmodel.subscribers_history.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.viewmodel.domain.SampleData.Companion.series1
import es.rafapuig.pmdm.compose.viewmodel.domain.SampleData.Companion.series2
import es.rafapuig.pmdm.compose.viewmodel.domain.SampleData.Companion.series3
import es.rafapuig.pmdm.compose.viewmodel.subscribers_history.ui.model.Series


private fun DrawScope.drawChartBorders(
    color: Color = Color.LightGray
) {
    drawRect(
        color = color,
        topLeft = Offset(0f, 0f),
        size = Size(size.width, size.height),
        style = Stroke(8f)
    )
}

private fun DrawScope.drawGridLines(
    gridLines: Int = 5,
    color: Color = Color.LightGray
) {
    val (width, height) = size
    (1..gridLines).forEach {
        val y = height / (gridLines + 1) * it
        drawLine(
            color = color,
            start = Offset(0f, y),
            end = Offset(width, y),
            strokeWidth = 2f
        )
    }
}

fun DrawScope.drawSeries(
    series: Series,
    minValue: Float = series.data.minOrNull() ?: 0f,
    maxValue: Float = series.data.maxOrNull() ?: 1f,
    pointCount: Int = series.data.size
) {
    val range = maxOf(0.001f, maxValue - minValue)

    /** Valores entre 0 y 1 */
    fun Float.normalize() = (this - minValue) / range

    val width = size.width
    val height = size.height

    /** Distancia entre cada punto en el eje X*/
    val horizontalSpacing = width / (pointCount - 1)

    val points = series.data
        .mapIndexed { index, value ->

            val x =
                (index + (pointCount - series.data.size)) * horizontalSpacing

            /**
             * Porque el eje Y se dibuja al revés
             * El 0 está arriba y el 1 abajo
             */
            val y = (1 - value.normalize()) * height

            Offset(x, y)
        }

    for (i in 0 until points.size - 1) {
        drawLine(
            series.color,
            points[i],
            points[i + 1],
            strokeWidth = 3f
        )
    }
}

@Composable
fun LineChart(
    vararg series: Series
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {

        /** El numero de elementos de la serie mas con mas elementos  */
        val n = series.maxOf { it.data.size }
        if (n < 2) return@Canvas

        val allValues = series.flatMap { it.data }
        val minValue = allValues.minOrNull() ?: 0f
        val maxValue = allValues.maxOrNull() ?: 1f

        drawGridLines(5)

        series.forEach {
            drawSeries(
                series = it,
                minValue = minValue,
                maxValue = maxValue,
                pointCount = n
            )
        }

        // Dibujar el borde del gráfico
        drawChartBorders()
    }
}


@Preview
@Composable
fun LineChartPreview() {
    LineChart(
        Series(series1.take(250).toList(), Color.Cyan),
        Series(series2.take(200).toList(), Color.Magenta),
        Series(series3.take(150).toList(), Color.Yellow)
    )
}