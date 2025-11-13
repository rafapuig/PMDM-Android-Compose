package es.rafapuig.pmdm.compose.learning.concepts.recomposition

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * Este estado sobrevive a los cambios de configuracion del dispositivo (orientacion, tema)
 * porque es una variable global.
 */
val counterState = mutableStateOf(0)

@Preview
@Composable
fun MyComposable() {
    Log.i("PMDM", "LLamada a la funcion MyComposable. Contador = ${counterState.value}")
    Box(
        modifier = Modifier.fillMaxSize().clickable {
            counterState.value++
        },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Contador: ${counterState.value}",
            fontSize = 50.sp
        )
    }
    Log.i("PMDM", "Fin de la funcion MyComposable. Contador = ${counterState.value}")
}