package es.rafapuig.pmdm.compose.sensors.level.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


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
        Box(
            modifier = modifier
                .height(height)
                .fillMaxWidth()
                .background(trackColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedProgress)
                    .background(color)
            )
        }
    } else {
        // ▮ VERTICAL ▮
        Box(
            modifier = modifier
                .width(width)
                .fillMaxHeight()
                .background(trackColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(animatedProgress)
                    .align(Alignment.BottomStart) // crece de abajo hacia arriba
                    .background(color)
            )
        }
    }
}
