package es.rafapuig.pmdm.compose.learning.concepts

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PastelTheme

/**
 * STATE HOISTING
 *
 * En UI complejas, mas de un Composable podría necesitar acceder al mismo estado.
 * En lugar de duplicar estados en cada Composable se usa el state hoisting.
 *
 * Implica mover el estado desde una función omposable hija hacia arriba
 * a una de las funciones llamadoras (padre o ancestros superiores)
 *
 * Cuando la función a la que hemos movido el estado llame esa funcion hija
 * que ha cedido el estado a dicha función composable ancestra
 * le proporcionará:
 * - el valor del estado
 * - un objeto función que cumple la el papel de manejador de eventos
 * Si ocurre un evento en el composable hijo que requiera una actualización
 * del valor del estado se llama al objeto función manejador de eventos ("la lambda")
 * pasándole como argumento el nuevo valor.
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
 *   eventos para modificar el estado que comparten
 * - Testing simplificado: Al centralizar el estado, es más facil probar cada Composable por separado
 */

@Composable
fun TextFieldStateHoistingDemoScreen() {
    /**
     * El estado ha sido movido hacia arriba a la función padre
     */
    var textState by remember { mutableStateOf("") }

    /**
     * Se crea un manejador de eventos mediante un objeto función
     * inicializado con una lambda que al tener acceso a la variable textState
     * por estar definida en el scope de la función donde se declara la variable
     * permite actualizar el valor del estado
     */
    val onTextChange = { newText: String -> textState = newText }

    /**
     * Cuando se llama al composable hijo se le pasa el estado y el manejador de eventos
     */
    TextFieldStateHoisted(textState, onTextChange)

    /**
     * Ahora el texto instroducido en el Textfield de TextFieldStateHoisted
     * es conocido por esta funcion padre y puede pasaarlo a otras composables
     */
}

/**
 * La función TextFieldStateHoisted al mover arriba el estado es ahora STATELESS
 *
 * Del composable llamador (padre)
 * recibimos el valor actual del estado en el parámetro text
 * y el manejador del evento onValueChange en el parámetro onTextChange
 *
 */
@Composable
fun TextFieldStateHoisted(text: String, onTextChange: (String) -> Unit) {
    /**
     * Hacemos el hoisting y subimos el estado al composable padre
     */
    TextField(value = text, onValueChange = onTextChange)
}

@Preview
@Composable
fun TextFieldStateHoistingDemoScreenPreview() {
    PastelTheme {
        TextFieldStateHoistingDemoScreen()
    }
}