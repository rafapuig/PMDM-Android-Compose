package es.rafapuig.pmdm.compose.learning.concepts

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PMDMComposeTheme

/**
 * STATE HOISTING
 *
 * Implica mover el estado desde una funcion composable hija hacia arriba
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
 * Con ello conseguimos que  * el composable hijo sea STATELESS
 * (y con ello mas facil de reusar)
 * y poder pasar el mismo estado desde el ancestro a otras composables hijas
 *
 * Se debe subir el estado hasta el ancestro común a dos composables descendentes
 * que requieran usar dicho estado
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
    PMDMComposeTheme {
        TextFieldStateHoistingDemoScreen()
    }
}