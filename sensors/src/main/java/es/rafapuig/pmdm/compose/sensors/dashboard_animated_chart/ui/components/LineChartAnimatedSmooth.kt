package es.rafapuig.pmdm.compose.sensors.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun rememberStableAnimationTrigger(minIntervalMs: Long = 120): Boolean {
    var lastUpdate by remember { mutableStateOf(0L) }
    val now = System.currentTimeMillis()

    val shouldAnimate = now - lastUpdate > minIntervalMs
    if (shouldAnimate) lastUpdate = now

    return shouldAnimate
}


@Composable
fun LineChartAnimatedSmooth(
    series1: List<Float>,
    series2: List<Float> = emptyList(),
    series3: List<Float> = emptyList(),
    labels: List<String>,
    colors: List<Color> = listOf(Color.Cyan, Color.Magenta, Color.Yellow),
    durationMs: Int = 20 // duración de la transición entre frames
) {
    // historial objetivo y previo (tienen la misma longitud visual que HISTORY_SIZE)
    val target1 = remember { mutableStateOf(series1.toList()) }
    val target2 = remember { mutableStateOf(series2.toList()) }
    val target3 = remember { mutableStateOf(series3.toList()) }

    val prev1 = remember { mutableStateOf(series1.toList()) }
    val prev2 = remember { mutableStateOf(series2.toList()) }
    val prev3 = remember { mutableStateOf(series3.toList()) }

    // progreso global de la animación 0..1
    val progress = remember { Animatable(1f) } // empieza en 1 (no interpolación)
    val scope = rememberCoroutineScope()

    val shouldAnimate = rememberStableAnimationTrigger(120)

    // When the incoming series change, shift prev<-target, update target and animate progress 0->1
    LaunchedEffect(series1, series2, series3) {
        // if sizes differ, align lengths by padding with zeros on the left to keep indices meaningful
        fun align(prev: List<Float>, next: List<Float>): Pair<List<Float>, List<Float>> {
            val maxSize = max(prev.size, next.size)
            val p = List(maxSize - prev.size) { 0f } + prev
            val n = List(maxSize - next.size) { 0f } + next
            return Pair(p, n)
        }

        val (aPrev1, aNext1) = align(prev1.value, series1)
        val (aPrev2, aNext2) = align(prev2.value, series2)
        val (aPrev3, aNext3) = align(prev3.value, series3)

        prev1.value = aPrev1
        prev2.value = aPrev2
        prev3.value = aPrev3

        target1.value = aNext1
        target2.value = aNext2
        target3.value = aNext3

        if (shouldAnimate) {
            progress.snapTo(0f)
            progress.animateTo(1f, tween(120))
        } else {
            progress.snapTo(1f)   // <- SIN ANIMACIÓN (evita flicker)
        }
        /**
        // restart animation
        progress.snapTo(0f)
        // animate to 1f
        scope.launch {
            progress.animateTo(1f, animationSpec = tween(durationMillis = durationMs))
        }*/
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        val width = size.width
        val height = size.height

        // gather all animated values (interpolated) to compute min/max
        val t = progress.value.coerceIn(0f, 1f)
        fun interp(prev: List<Float>, target: List<Float>, idx: Int): Float {
            val p = prev.getOrNull(idx) ?: 0f
            val q = target.getOrNull(idx) ?: 0f
            return p * (1 - t) + q * t
        }

        val maxPoints = maxOf(prev1.value.size, prev2.value.size, prev3.value.size,
            target1.value.size, target2.value.size, target3.value.size)
        if (maxPoints < 2) return@Canvas

        // Build lists of interpolated values for normalization
        val anim1 = List(maxPoints) { idx -> interp(prev1.value, target1.value, idx) }
        val anim2 = List(maxPoints) { idx -> interp(prev2.value, target2.value, idx) }
        val anim3 = List(maxPoints) { idx -> interp(prev3.value, target3.value, idx) }

        val all = anim1 + anim2 + anim3
        val minVal = all.minOrNull() ?: 0f
        val maxVal = all.maxOrNull() ?: 1f
        val range = max(0.01f, maxVal - minVal)

        fun Float.normalizeY(): Float = height - ((this - minVal) / range) * height

        // draw grid lines
        val gridLines = 4
        for (i in 0..gridLines) {
            val y = i * height / gridLines
            drawLine(Color.DarkGray, Offset(0f, y), Offset(width, y), strokeWidth = 1f)
        }

        // draw a helper center line
        drawLine(Color.Gray, Offset(0f, height / 2f), Offset(width, height / 2f), strokeWidth = 1f)

        // draw function to draw a series
        fun drawSeries(data: List<Float>, color: Color) {
            val pointCount = data.size
            if (pointCount < 2) return
            val stepX = width / (pointCount - 1).coerceAtLeast(1)
            for (i in 0 until pointCount - 1) {
                val x1 = i * stepX
                val y1 = data[i].normalizeY()
                val x2 = (i + 1) * stepX
                val y2 = data[i + 1].normalizeY()
                drawLine(color, Offset(x1, y1), Offset(x2, y2), strokeWidth = 3f)
            }
        }

        drawSeries(anim1, colors.getOrElse(0) { Color.Cyan })
        if (series2.isNotEmpty()) drawSeries(anim2, colors.getOrElse(1) { Color.Magenta })
        if (series3.isNotEmpty()) drawSeries(anim3, colors.getOrElse(2) { Color.Yellow })

        // optional: legend drawn on native canvas
        drawIntoCanvas { canvas ->
            val paint = android.graphics.Paint().apply {
                color = android.graphics.Color.WHITE
                textSize = 28f
                isAntiAlias = true
            }
            // small legend
            canvas.nativeCanvas.drawText("Live", 8f, 22f, paint)
        }
    }
}
