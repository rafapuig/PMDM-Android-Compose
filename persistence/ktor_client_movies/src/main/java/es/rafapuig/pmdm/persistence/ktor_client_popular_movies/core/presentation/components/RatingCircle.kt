package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun RatingCircle(
    rating: Double, // Valor de 0.0 a 10.0
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    strokeWidth: Dp = 3.dp
) {
    // Normalizamos el rating a un valor entre 0.0 y 1.0
    val progress = rating / 10.0

    // Determinamos el color del arco basado en el rating
    val progressColor = when {
        rating >= 7.0 -> Color(0xFF21D07A) // Verde TMDb
        rating >= 4.0 -> Color(0xFFD2D531) // Amarillo TMDb
        else -> Color(0xFFDB2360)          // Rojo TMDb
    }

    val trackColor = Color(0xFF204529) // Color de fondo oscuro TMDb

    val backgroundColor = Color.Black.copy(alpha = 0.6f)

    // Convertimos el rating a porcentaje para mostrarlo
    val percentage = (progress * 100).roundToInt()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        // Usamos Canvas para dibujar los círculos
        Canvas(modifier = Modifier.size(size)) {

            // 1. Dibuja el fondo circular semitransparente
            drawCircle(
                color = backgroundColor
                // No se especifica 'style', por lo que por defecto es 'Fill' (relleno)
            )

            // Esto crea el espacio entre el borde del fondo y el track.
            val innerRadius = (this.size.minDimension / 2) - strokeWidth.toPx()


            // 2. Círculo de fondo (track)
            drawCircle(
                radius = innerRadius,
                color = trackColor,
                style = Stroke(width = strokeWidth.toPx())
            )

            // 3. Arco de progreso
            drawArc(
                color = progressColor,
                startAngle = -90f, // Empezar desde la parte superior
                sweepAngle = (360 * progress).toFloat(),
                useCenter = false, // No queremos un "quesito", sino un arco
                // Le damos la posición y el tamaño del área de dibujo más pequeña
                topLeft = center - Offset(innerRadius, innerRadius),
                size = Size(innerRadius * 2, innerRadius * 2),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round) // Bordes redondeados
            )
        }

        val numberFontSize = (size.value / 3.8).sp
        val symbolFontSize = (size.value / 6.0).sp

        // 3. Texto con el porcentaje en el centro
        Text(
            text = buildAnnotatedString {
                // 1. Añade el número con el tamaño de fuente grande
                withStyle(style = SpanStyle(fontSize = numberFontSize)) {
                    append(percentage.toString())
                }
                // 2. Añade el símbolo '%' con el tamaño de fuente pequeño
                withStyle(style = SpanStyle(
                    fontSize = symbolFontSize,
                    baselineShift = BaselineShift(0.3f)
                )) {
                    append("%")
                }
            },
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

// Preview para ver cómo queda en Android Studio
@Preview(showBackground = true, backgroundColor = 0xFF081C22)
@Composable
fun RatingCirclePreview() {
    RatingCircle(rating = 7.8)
}

@Preview(showBackground = true, backgroundColor = 0xFF081C22)
@Composable
fun RatingCircleMediumPreview() {
    RatingCircle(rating = 5.2)
}

@Preview(showBackground = true, backgroundColor = 0xFF081C22)
@Composable
fun RatingCircleLowPreview() {
    RatingCircle(rating = 2.5)
}
