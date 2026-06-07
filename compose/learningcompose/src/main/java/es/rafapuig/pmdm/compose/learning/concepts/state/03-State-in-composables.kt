package es.rafapuig.pmdm.compose.learning.concepts.state

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.concepts.recomposition.TAG

/**
 * Estados en funciones composables
 *
 * Una función composable idealmente debe ejecutarse para producir un pedazo de UI
 * Si realiza acciones adicionales, estas se consideran efectos secundarios o colaterales.
 *
 * Debemos tener en cuenta que una función composable se llama por el runtime de Compose
 * no solamente una vez (composición inicial) sino que puede ser llamada varias veces
 * (recomposiciones) dado que es la única manera de actualizar el aspecto de la UI
 * producida por el composable.
 *
 * Si una función composable contiene instrucciones que realizan efectos secundarios,
 * esto ser volverán a ejecutar cuando se produzca una recomposición.
 *
 * Uno de estos efectos secundarios podría ser la creación de un objeto estado.
 */

@SuppressLint("UnrememberedMutableState") // Anotación para que no de fallo
@Preview
@Composable
fun MyComposableWithSideEffects() {

    Log.i("MyComposableWithSideEffects", "Recomposición")

    /**
     * Esta instrucción se ejecuta en cada recomposición
     * creando un objeto estado nuevo cada vez.
     */
    val nameState = mutableStateOf("Rafa")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            // Usamos el valor del estado nuevo que se crea en cada recomposición
            value = nameState.value,
            onValueChange = {
                // Cambiamos el valor del estado para producir una recomposición
                // Pero cuando se vuelva a ejecutar la función composable
                // se creará un nuevo objeto estado
                // y no se usará este valor
                nameState.value = it
            }
        )
    }
}

/**
 * La solución será que el estado solo se cree durante la composición inicial
 * y se reutilice durante las recomposiciones.
 * Por tanto, necesitamos una función que ejecute la instrucción que crea el estado
 * durante la composición inicial y que devuelva el estado.
 * Esta función se llama remember.
 */

@Composable
fun rememberNameState(): MutableState<String> {
    Log.i(TAG, "Llamada a rememberNameState para obtener el estado")
    /**
     * La función remember ejecuta el código de calculation
     * solamente durante la composición inicial
     * y devuelve el valor devuelto por calculation.
     * Además, recuerda el valor producido durante la composición inicial
     * y es el valor que devuelve cuando es llamado en las recomposiciones.
     */
    return remember(
        calculation = {
            Log.i(TAG, "Creación del estado")
            mutableStateOf("Rafa")
        }
    )
}

@Preview
@Composable
fun MyComposableWithRemember() {
    Log.i(TAG, "Llamada a MyComposableWithRemember")

    /**
     * Ahora la función rememberNameState devuelve siempre el mismo objeto estado
     */
    val nameState = rememberNameState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = nameState.value,
            onValueChange = {
                nameState.value = it
            }
        )
    }
}


/**
 * Un composable que declara (y recuerda) algún estado
 * se clasifica como Stateful composable.
 */
@Preview
@Composable
fun MyStatefulComposable() {
    Log.i(TAG, "Llamada a MyStatefulComposable")

    val nameState = remember {
        Log.i(TAG, "Creación del estado nameState!")
        mutableStateOf("Rafa")
    }

    TextField(
        value = nameState.value,
        onValueChange = {
            nameState.value = it
        }
    )
}

@Preview
@Composable
fun MyStatefulComposablePreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MyStatefulComposable()
    }
}


