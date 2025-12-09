package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState.example2.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme

val buttonColors = listOf(
    Color.Red,
    Color.Blue,
    Color.Green,
    Color.Yellow,
    Color.Magenta,
    Color.Cyan
)

@Composable
fun ColorButton(
    color: Color,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .then(
                if (isSelected)
                    Modifier
                        .border(
                            3.dp,
                            MaterialTheme.colorScheme.onBackground,
                            CircleShape
                        )
                //.padding(16.dp)
                else Modifier
            )
            .background(color)
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ColorButtonPreview() {
    PMDMComposeTheme {
        Surface {
            ColorButton(Color.Red, true)
        }
    }
}