package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * SideEffect se llama cada vez que se produce una recomposición con éxito
 *
 * Compartir estado de Compose con código no-Compose
 */

@Preview(showBackground = true)
@Composable
fun SideEffectDemo() {

    var checked by remember { mutableStateOf(false) }

    Switch(
        modifier = Modifier.fillMaxSize(),
        checked = checked,
        onCheckedChange = {
            Log.i("PMDM","Cambio de estado del Switch")
            checked = it
        }
    )

    SideEffect {
        println("Llamada cada vez que hay recomposición con éxito")
    }

}