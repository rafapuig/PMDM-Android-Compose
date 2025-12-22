package es.rafapuig.pmdm.compose.learning.concepts.recomposition.intro1

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

/**
 * Al no ser un estado, una modificacion de su valor no provoca una recomposición
 */
var counter = 0

/**
 * Si cambiar el valor del estado toogle (de verdadero a falso o de falso a verdadero)
 * se provoca una recomposición
 * y podremos ver el valor actual de la variable counter
 */
val toggle = mutableStateOf(false)

@Preview
@Composable
fun MyComposable() {
    Log.i("PMDM", "LLamada MyComposable. Contador = ${counter} ${toggle.value}")
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {

            Text(
                text = "Contador: ${counter}",
                fontSize = 52.sp,
                modifier = Modifier.clickable {
                    Log.i("PMDM", "Incrementando el contador con valor = $counter ...")
                    counter++
                    Log.i("PMDM", "Nuevo valor del contador = ${counter}")
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
            Text(text = "Valor del estado: ${toggle.value}")
        }
    }
    Log.i("PMDM", "Fin de MyComposable. Contador = ${counter}") // ${toggle.value}")
}