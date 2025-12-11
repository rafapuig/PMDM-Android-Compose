package es.rafapuig.pmdm.compose.exercises.side_effects

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.exercises.ui.theme.ComposeExercisesTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import kotlin.time.Duration.Companion.seconds

/**
 * Crea una pantalla donde el usuario elige entre varios colores
 * y pasado 5 segundos desde la elección se pone como color de fondo
 * de un rectangulo.
 *
 * Se podrá ver una cuenta atras desde 5 hasta 0 dentro del rectangulo
 *
 * El rectangulo tiene que ser un composable independiente
 * que se encarga de realizar su cuenta atras y cambiar al final de color
 *
 * Ahora empieza una cuenta atras de 5 segundos
 * y se pondrá como color de fondo el ultimo color seleccionado
 * pero no se puede reiniciar la cuenta cuando cambie el color seleccionada
 *
 */


private fun counterFlow(from: Int = 0) =
    (from downTo 1).asFlow().transform {
        emit(it)
        delay(1.seconds)
    }


@Composable
fun CounterDown2(
    selectedColor: Color
) {
    var color by remember { mutableStateOf(Color.Unspecified) }
    var counter by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth(.5f)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10))
            .background(color)
            .border(4.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(10))
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(counter, style = MaterialTheme.typography.displayLarge)
    }

    val updatedSelectedColor by rememberUpdatedState(selectedColor)

    LaunchedEffect(Unit) {

        counterFlow(5)
            .onEach { counter = it.toString() }
            .collect()

        color = updatedSelectedColor
        counter = ""
    }

}

@OptIn(FlowPreview::class)
@Composable
fun ColoredWithDelay4(
    modifier: Modifier = Modifier
) {
    println("Recomposición")

    val colors = remember { listOf(Color.Red, Color.Blue, Color.Green, Color.Magenta) }

    var selectedColorIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text("Coloreado con retardo")

        CounterDown2(if(selectedColorIndex == -1) Color.Unspecified else colors[selectedColorIndex])

        Row(
            modifier = Modifier.fillMaxWidth(.8f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            colors.forEachIndexed { index, color ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(color)
                        .then(
                            if (index == selectedColorIndex)
                                Modifier.border(
                                    5.dp,
                                    MaterialTheme.colorScheme.onSurface,
                                    CircleShape
                                )
                            else Modifier
                        )
                        .clickable {
                            selectedColorIndex = index
                        }
                )
            }
        }


        Spacer(modifier = Modifier.height(24.dp))
        var toggle by remember { mutableStateOf(false) }
        Switch(
            checked = toggle,
            onCheckedChange = {
                toggle = it
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ColoredWithDelay4Preview() {
    ComposeExercisesTheme {
        Scaffold { innerPadding ->
            ColoredWithDelay4(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
