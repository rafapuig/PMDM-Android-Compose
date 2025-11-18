package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.produce

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components.CircleColoredWithNumber
import es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components.ColorSelectorRow
import es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components.buttonColors
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun ProduceStateDemo() {

    var selectedIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircleColored(
            color = buttonColors[selectedIndex],
            size = 200.dp
        )

        ColorSelectorRow(
            selectedIndex = selectedIndex,
            onColorSelected = { index ->
                selectedIndex = index
            }
        )
    }
}


@Composable
fun CircleColored(
    color: Color = Color.Black,
    size: Dp = 100.dp
) {
    var circleColor by remember {
        mutableStateOf(Color.DarkGray)
    }

    val currentColor by rememberUpdatedState(newValue = color)

    val counter by produceState(5) {
        while (value > 0) {
            delay(1.seconds)
            value--
        }
        //circleColor = color
        circleColor = currentColor // Asi si usa el ultimo color
    }

    CircleColoredWithNumber(size, circleColor, counter)
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun ProduceStateDemoPreview() {
    PMDMComposeTheme {
        Surface {
            ProduceStateDemo()
        }
    }
}



