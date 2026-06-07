package es.rafapuig.pmdm.compose.learning.concepts.recomposition

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlin.random.Random

const val TAG = "PMDM"

/**
 * Este estado sobrevive a los cambios de configuración del dispositivo
 * (orientación, tema) porque es una variable global.
 */
val counterState = mutableIntStateOf(0)

/**
 * Pulsa el icono de la izquierda de la función para iniciarla en un dispositivo
 * (no sirve solo con previsualizar)
 */
@Preview
@Composable
fun MyComposable() {

    /**
     * Enviar un mensaje al LOG para poder visualizarlo mediante el LogCat
     */
    Log.i(TAG, "LLamada a la funcion MyComposable.")
    Log.i(TAG, "Contador = ${counterState.intValue}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                /**
                 * Si no se modifica el estado del contador
                 * no hace falta recomponer la "pieza" de UI que genera MyComposable
                 * y, por tanto, no se vuelve a llamar a MyComposable,
                 * pero cuando se modifica el valor del estado del contador
                 * entonces hay que volver a "pintar" las partes de la UI
                 * que dependen del valor de contador y, por tanto, se llama al
                 * composable MyComposable
                 */
                if (Random.nextBoolean()) {
                    Log.i("PMDM", "Incremento del valor del estado contador")
                    counterState.intValue++
                } else {
                    Log.i("PMDM", "No se modifica el estado del contador")
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hola", // "Contador: ${counterState.intValue}",
            fontSize = 50.sp
        )
    }
    /**
     * Enviar un mensaje al LOG para poder visualizarlo mediante el LogCat
     * que se debe mostrar cuando ha terminado la ejecución
     * de la función composable
     */
    Log.i(TAG, "Fin de la funcion MyComposable")
}