package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState.example2

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState.example2.components.CircleColoredWithNumber
import es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState.example2.components.ColorSelectorRow
import es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState.example2.components.buttonColors
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun CircleColored(
    color: Color = Color.Black,
    size: Dp = 100.dp
) {
    var counter by remember { mutableStateOf(5) }

    var circleColor by remember { mutableStateOf(Color.DarkGray) }

    val updatedColor by rememberUpdatedState(newValue = color)

    /**
     * Countador para contar hacia atrás 5 segundos
     * antes de establecer el color de fondo del círculo
     *
     * Solamente se llama durante la primera composición de CircleColored
     */
    LaunchedEffect(Unit) {
        while (counter > 0) {
            delay(1000.milliseconds)
            counter--
        }
        /**
         * Esto no funciona porque el LaucherEffect se queda
         * con el primer valor de color con que que se compuso
         * En sucesivas recomposiones cuando cambia el valor
         * del parametro color de CircleColored ya no se tiene en cuenta
         */
        //circleColor = color

        /**
         * Para que use el último color se usa el updatedColor
         * gracias rememberUpdatedState
         * se usa el ultimo valor actualizado sin reinciar el LaucherEffect
         */
        circleColor = updatedColor
    }

    CircleColoredWithNumber(
        size,
        circleColor,
        counter
    )
}


@Preview(showBackground = true)
@Composable
fun RememberUpdateStateContent() {

    var restartKey by remember { mutableStateOf(0) }

    key(restartKey) {

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

            Button(onClick = { restartKey++ }) {
                Text("Reiniciar")
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun RememberUpdateStateContentPreview() {
    PMDMComposeTheme {
        Surface {
            RememberUpdateStateContent()
        }
    }
}

