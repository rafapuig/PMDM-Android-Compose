package es.rafapuig.pmdm.sensors.level.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val roundedPercent = 33

@Composable
fun RectangularProgressIndicator(
    progress: Float,                  // 0f..1f
    modifier: Modifier = Modifier,
    height: Dp = 6.dp,                // usado en horizontal
    width: Dp = 6.dp,                 // usado en vertical
    vertical: Boolean = false,        // ⬆️⬇️ Modo vertical
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        label = "progressAnimation"
    )

    if (!vertical) {
        // ▬▬▬▬ HORIZONTAL ▬▬▬▬
        HorizontalProgressIndicator(animatedProgress, modifier, height, color, trackColor)
    } else {
        // ▮ VERTICAL ▮
        VerticalProgressIndicator(animatedProgress, modifier, width, color, trackColor)
    }
}

@Composable
private fun VerticalProgressIndicator(
    animatedProgress: Float,
    modifier: Modifier,
    width: Dp,
    color: Color,
    trackColor: Color
) {
    Box(
        modifier = modifier
            .width(width)
            .fillMaxHeight()
            .clip(RoundedCornerShape(roundedPercent))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(animatedProgress)
                .align(Alignment.BottomCenter) // aumenta de abajo a arriba
                .clip(
                    RoundedCornerShape(
                        topStartPercent = roundedPercent,
                        topEndPercent = roundedPercent
                    )
                )
                .background(color)
        )
    }
}

@Composable
private fun HorizontalProgressIndicator(
    animatedProgress: Float,
    modifier: Modifier,
    height: Dp,
    color: Color,
    trackColor: Color
) {
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .clip(RoundedCornerShape(roundedPercent))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animatedProgress)
                .clip(RoundedCornerShape(
                    topEndPercent = roundedPercent,
                    bottomEndPercent = roundedPercent)
                )
                .background(color)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RectangularProgressIndicatorPreview() {
    RectangularProgressIndicator(
        progress = 0.2f,
        modifier = Modifier.width(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun VerticalProgressIndicatorPreview() {
    RectangularProgressIndicator(
        progress = 0.75f,
        vertical = true,
        modifier = Modifier.height(200.dp)
    )
}
