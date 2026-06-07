package es.rafapuig.pmdm.compose.learning.concepts

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PastelTheme

/**
 * STATE HOISTING
 *
 * State hoisting en Compose es un patron
 * que consiste en mover el estado a un composable llamador
 * para convertir el composable en stateless.
 *
 * El patrón general para realizar un state hoisting en Jetpack Compose
 * consiste en reemplazar la variable local que contiene el estado por dos parámetros:
 * - value: T: el valor actual del estado
 * - onValueChange: (T) -> Unit: un evento que solicita el cambio de valor del estado,
 * siendo T el nuevo valor propuesto
 */

/**
 * En UI complejas, más de un Composable podría necesitar acceder al mismo estado.
 * En lugar de duplicar estados en cada Composable se usa el state hoisting.
 *
 * Implica mover el estado desde una función composable hija hacia arriba
 * a una de las funciones llamadoras (padre o ancestros superiores)
 *
 * Cuando la función a la que hemos movido el estado llame esa función hija
 * que ha cedido el estado a dicha función composable ancestra
 * le proporcionará:
 * - el valor del estado
 * - un objeto función que cumple el papel de manejador de eventos
 * Si ocurre un evento en el composable hijo que requiera una actualización
 * del valor del estado se llama al objeto función manejador de eventos ("la lambda")
 * pasándole como argumento el nuevo valor del estado.
 *
 * Con ello conseguimos que:
 * - el composable hijo sea STATELESS (y con ello más facil de reusar)
 * - poder pasar el mismo estado desde el ancestro a otras composables hijas
 *   (compartir el estado entre composables)
 *
 * Se debe subir el estado hasta el ancestro común a dos composables descendentes
 * que requieran usar dicho estado
 *
 * Beneficios:
 * - Single Source of Truth: Solo hay una copia del estado (un solo objeto)
 *   (lo que evita inconsistencias)
 * - Mejor comunicación: Los composables hijos se comuninan entre ellos provocando
 *   eventos para modificar el estado que comparten.
 * - Testing simplificado: Al centralizar el estado, es más facil probar cada composable
 *   por separado.
 * - Desacoplamiento: el estado de los composables stateless se puede almacenar en cualquier
 *   lugar. Por ejemplo, en un ViewModel.
 */

@Composable
fun TextFieldStateHoistingDemoScreen() {
    /**
     * El estado ha sido movido hacia arriba a la función padre TextFieldStateHoistingDemoScreen
     */
    var textState = remember { mutableStateOf("") }

    /**
     * Se crea un manejador de eventos mediante un objeto función
     * inicializado con una lambda que al tener acceso a la variable textState
     * por estar definida en el scope de la función donde se declara la variable
     * permite actualizar el valor del estado
     */
    val onTextChange = { newText: String ->
        textState.value = newText
    }

    Column {
        /**
         * Cuando se llama al composable hijo se le pasa el valor del estado
         * y el manejador de eventos que actualiza el valor del estado
         */
        TextFieldStateHoisted(
            text = textState.value,
            onTextChange = onTextChange
        )

        /**
         * Ahora el texto introducido en el TextField de TextFieldStateHoisted
         * es conocido por esta función padre y puede pasarlo a otras composables
         */
        Text(textState.value)

        /**
         * También puede usarlo en su beneficio para tomar decisiones
         */
        if (textState.value.length > 5) {
            Text("El texto es largo")
        }

        Button(
            enabled = textState.value.isNotEmpty(),
            onClick = { textState.value = "" }) {
            Text("Clear")
        }
    }
}

/**
 * La función TextFieldStateHoisted al mover arriba el estado es ahora STATELESS
 *
 * Del composable llamador (padre)
 * recibimos como argumentos:
 * - el valor actual del estado en el parámetro text
 * - el manejador del evento onValueChange en el parámetro onTextChange
 *
 */
@Composable
fun TextFieldStateHoisted(
    text: String,
    onTextChange: (String) -> Unit
) {
    /**
     * Hacemos el hoisting y subimos el estado al composable padre
     */
    TextField(value = text, onValueChange = onTextChange)
}

@Preview(showBackground = true)
@Composable
fun TextFieldStateHoistingDemoScreenPreview() {
    PastelTheme {
        TextFieldStateHoistingDemoScreen()
    }
}