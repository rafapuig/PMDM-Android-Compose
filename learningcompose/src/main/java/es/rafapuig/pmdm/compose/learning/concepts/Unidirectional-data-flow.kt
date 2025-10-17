package es.rafapuig.pmdm.compose.learning.concepts

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * UNIDIRECTIONAL DATA FLOW
 *
 * Es un metodo para del desarrollo de apps donde
 * - el estado almacenado en un composable
 * - NO se debe cambiar directamente en ninguna de las composables hijas
 */

@Composable
fun UnidirectionaldataflowDemoScreen() {
    ParentFunctionWithState()
}

@Composable
fun ParentFunctionWithState() {
    /**
     * Es la funcion donde declaramos el estado
     */
    var switchState by remember { mutableStateOf(false) }

    /**
     * Declaramos la función para cambiar el estado
     *
     * La expresión lambda recibe el nuevo estado y puede acceder la
     * la variable que contiene el valor del estado para modificarlo
     * (Una lambda puede acceder a las variables declaradas en la función
     * donde esta definida, esto se llama clausura, por eso definimos
     * la lambda en la misma función donde se declara el estado)
     */
    val onSwitchChange = { newState: Boolean ->
        switchState = newState
    }

    /**
     * A las funciones composables hijas les pasamos
     * el estado
     * y la función para cambiar el estado
     */
    ChildFunctionReceivesState(switchState, onSwitchChange)
}

@Composable
fun ChildFunctionReceivesState(
    switchState: Boolean,
    onSwitchChange: (Boolean) -> Unit
) {
    Switch(switchState, onSwitchChange)
}