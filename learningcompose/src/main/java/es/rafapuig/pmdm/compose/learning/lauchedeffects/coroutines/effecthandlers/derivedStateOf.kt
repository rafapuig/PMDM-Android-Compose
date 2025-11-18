package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines.effecthandlers

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.lang.Math.pow
import kotlin.math.pow

/**
 * Con derivedStateOf el calculo del valor derivado de un estado
 * se cachea y no se vuelve a recalcular en cada recomposición, solamente cuando
 * el estado del que deriva cambia
 */

@Preview
@Composable
fun DerivedStateDemo() {

    var numberText by remember {
        mutableStateOf("")
    }

    /**
     * Nos evitaremos recomposiciones si el estado de enabled no cambia
     * Solamente en condiciones umbral, cuando pasamos de 0 a 1 caracteres
     */
    val enabled by remember { derivedStateOf { numberText.isNotEmpty() } }
    //val enabled = numberText.isNotEmpty()


    /**
     * No es correcto usar derivedStateOf aquí,
     * cuando cambiar un valor siempre cambia el otro
     */
    val square by derivedStateOf {
        Log.i("PMDM", "Cacheando el cuadrado...")
        (numberText.toDoubleOrNull() ?: 0.0).pow(2.0).toInt()
    }

    val nonCachedSquare = run {
        Log.i("PMDM", "Recalculando el cuadrado...")
        (numberText.toDoubleOrNull() ?: 0.0).pow(2.0).toInt()
    }


    /*var square by remember {
     mutableStateOf(0)
 }*/
    /*LaunchedEffect(numberText) {
        square = (numberText.toDoubleOrNull() ?: 0.0).pow(2.0).toInt()
    }*/


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = numberText,
            onValueChange = {
                numberText = it
            }
        )
        Text(text = "Cuadrado: $square")
        Text(text = "Cuadrado: $nonCachedSquare")

        /**
         * Probar las recomposiciones del Button que nos ahorramos con el Layer Inspector
         */
        Button(
            onClick = {},
            enabled = enabled
        ) {
            Text(text = "Calcular")
        }
    }
}