package es.rafapuig.pmdm.compose.exercises.side_effects

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

/**
 * Crea una función composable Recomposition que desencadene
 * una repomposición que sea provocada por un composable Switch
 * Asegurate de que se recompone colocando println's
 * y mirando el LogCat o
 * mirando el LayerI Inspector de Android Studio
 */

@Preview
@Composable
fun RecompositionDemo() {

    println("RecompositionDemo")

    var checked by remember { mutableStateOf(false) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )
}
