package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components.CircleColoredWithNumber
import es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components.ColorButton
import es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components.ColorSelectorRow
import es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers.components.buttonColors
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Preview(showBackground = true)
@Composable
fun RememberUpdateStateDemo2() {

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



@Preview
@Composable
fun CircleColored(
    color: Color = Color.Black,
    size: Dp = 100.dp
) {
    var counter by remember {
        mutableStateOf(5)
    }

    var circleColor by remember {
        mutableStateOf(Color.DarkGray)
    }

    val currentColor by rememberUpdatedState(newValue = color)

    LaunchedEffect(Unit) {
        while (counter > 0) {
            delay(1000.milliseconds)
            counter--
        }
        /**
         * Esto no funciona porque el LaucherEffect se queda
         * con el primer valor de color con que que se compuso
         * En sucesivas recomposiones cuando cambia el valor
         * del parametro color no se tiene en cuenta
         */
        //circleColor = color
        /**
         * Para que use el último color se usa
         * el rememberUpdatedState
         */
        circleColor = currentColor
    }

    CircleColoredWithNumber(
        size,
        circleColor,
        counter
    )
}

