package es.rafapuig.pmdm.compose.learning.concepts.`unidirectional-data-flow`

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

/**
 * Para la gestion del estado de la UI Compose promueve un modelo de flujo de datos unidireccional:
 * Unidirectional Data Flow (UDF)
 *
 * Esto quiere decir que el estado de la UI fluye en una unica dirección (hacia abajo):
 *
 * Hay dos flujos:
 *
 *  - Downward Flow:
 *      El estado de la UI fluye hacia abajo desde el composable padre hacia los hijos.
 *      (Desde la función composable padre o llamadora a las funciones llamadas o hijas)
 *
 *  - Upward Flow:
 *      Los EVENTOS (por ejemplo clicks o entradas en un TextField)
 *      fluyen hacia arriba desde los composables hijos hacia los ancestros.
 *      Los hijos emiten eventos y los padres los manejan modificando el estado de la UI,
 *      El cambio en el estado de la UI produce a su vez una recomposición.
 *      La recomposicion hace que se vuelva a llamar a las funciones que dependen del
 *      estado cuyo valor ha cambiado.
 */

/**
 * El componente Counter es estateless
 * El compomente padre será responsable de contener y actualizar el estado del contador
 * y pasarselo (hacia abajo) al composable Counter
 */
@Composable
fun Counter(
    count: Int, // valor del estado en el momento de llamada, determina el valor a mostrar en la UI
    onIncrement: () -> Unit // es el manejador del evento click que fluye arriba hacia el padre
) {
    Column {
        Text(text = "Count: $count")
        Button(onClick = onIncrement) {
            Text(text = "Incrementar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Parent() {
    /**
     * Estado observable por Compose (de un valor de tipo Int)
     * La funcion remember cachea el objeto
     * de forma que solamente se crea primera vez que se llama a Parent (en la primera composición)
     * y despues se devuelve el mismo objeto en las recomposiciones.
     */
    val counterState = remember { mutableIntStateOf(0) }

    /**
     * Manejador del evento click sobre el botón de incremento del contador
     * Se ejecutará en el padre (se pasará al hijo para que lo llame)
     * y se modificará valor del estado del contador
     */

    val onIncrement: () -> Unit = { counterState.value++ }

    Counter(
        count = counterState.value, // pasamos el valor del estado al composable hijo
        onIncrement = onIncrement // pasamos el manejador del evento click al composable hijo
    )
}