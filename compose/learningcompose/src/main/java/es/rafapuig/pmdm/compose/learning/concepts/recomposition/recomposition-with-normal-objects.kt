package es.rafapuig.pmdm.compose.learning.concepts.recomposition.nostate

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.concepts.recomposition.TAG

/**
 * Al no ser counter un estado,
 * una modificación de su valor es observable por Compose
 * y no provoca una recomposición
 */
var counter = 0

/**
 * Cambiar el valor del estado toogle (de verdadero a falso o de falso a verdadero)
 * si provoca una recomposición (porque MyComposable observa toggle dado que lo lee)
 * y podremos ver el valor actual de la variable counter
 */
val toggle = mutableStateOf(false)

@Preview
@Composable
fun MyComposable() {
    /**
     * Notificar en el LogCat que se ha llamado a MyComposable y valor de counter
     */
    Log.i(TAG, "LLamada MyComposable. Contador = ${counter}") // ${toggle.value}")

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {

            Text(
                text = "Contador: $counter",
                fontSize = 52.sp,
                modifier = Modifier.clickable {
                    Log.i(TAG, "Incrementando el contador con valor = $counter ...")
                    counter++
                    Log.i(TAG, "Nuevo valor del contador = $counter")
                }
            )
            Button(
                onClick = {
                    toggle.value = true
                },
            ) {
                Text(text = "Activar")
            }
            Button(
                onClick = {
                    toggle.value = false
                },
            ) {
                Text(text = "Deactivar")
            }
            Button(
                onClick = {
                    toggle.value = !toggle.value
                },
            ) {
                Text(text = "Alternar")
            }
            //Text(text = "Valor del estado: ${toggle.value}")
        }
    }

    /**
     * Notificar en el LogCat que se ha finalizado MyComposable y valor de counter
     */
    Log.i(TAG, "Fin de MyComposable. Contador = $counter") // ${toggle.value}")
}