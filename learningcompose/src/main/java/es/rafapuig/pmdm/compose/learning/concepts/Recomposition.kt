package es.rafapuig.pmdm.compose.learning.concepts

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme

/**
 * State
 *
 * El valor asignado a un State debe ser recordado (remember)
 * Cada vez que se llama a una función con estados (STATEFUL function)
 * esta debe recordar el valor que tenía cada estado la ultima vez que se llamó
 * a dicha función
 * (Las variables locales no recuedan su valor, puesto que se crean y reinicializan
 * en cada llamada a la función)
 *
 * Cada cambio es un State tiene consecuencias en toda la jerarquía de
 * composables que componen la UI
 */

/**
 * RECOMPOSICIÓN
 *
 * En Compose una UI se construye a base de crear jerarquias de funciones composables.
 * Una funcion composable usa datos para generar una seccion de la UI.
 *
 * La funcion puede haber recibido los datos como argumento de llamada desde otra
 * funcion composable que ha declarado un estado para mantener los datos que usa
 * como argumento.
 * Lo que implica que un cambio en el estado de un composable llamador
 * (padre o ancestro en la jerarquia de llamadas)
 * podría necesitar reflejarse en cualquiera de los composables hijos
 * a los que el valor del estado se pasa como argumento.
 *
 * Para ello, el runtime de compose realiza lo que se denomina una RECOMPOSICION
 *
 * Una recomposicion se lleva a caba cada vez que el valor de un estado
 * cambia dentro de una jerarquia de llamadas de funciones composables.
 * No es mas que volver a llamar a las funciones afectas pasandoles como
 * argumento el nuevo valor del estado.
 *
 * Solamente se "recomponen" las funciones afectadas por el estado que ha cambiado.
 * (Funciones que leen el valor del estado)
 */

@Composable
fun RecompositionDemoScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MyTextField()
        MyTextField2()
    }
}

/**
 * MyTextField es un composable stateful
 * declara una variable que contiene un State
 * y un manejador de eventos para actualizar el State a partird de la entrada
 * por teclado del usuario.
 */
@Composable
fun MyTextField() {
    /**
     * Un estado mutable se crea a partir de la funcion factoria mutableStateOf()
     * pasando como argumento el valor inicial del estado
     */
    val textState = mutableStateOf("")

    /**
     * Para retener el valor actual del estado necesitamos memorizarlo
     * con la función remember()
     */
    val rememberedTextState = remember { textState }

    // Un objeto función para actualizar el estado a partir del texto introducido por el usuario
    val onTextChange = { newText: String ->
        rememberedTextState.value = newText // Actualizamos el valor del estado
    }

    TextField(
        value = rememberedTextState.value, // Asignamos el valor actual del estado
        onValueChange = onTextChange // pasamos el objeto función manejador de eventos
    )
}

/**
 * MyTextfield (con propiedades delegadas)
 */
@Composable
fun MyTextField2() {
    /**
     * Utilizamos la delegación de propiedades (uso de by)
     * para simplificar el acceso al valor del estado
     * (Ahora rememberedText es una propiedad String delegada en el MutableState)
     */
    var rememberedText by remember { mutableStateOf("") }

    val onTextChange = { newText: String ->
        rememberedText = newText // Al settear el valor se actualiza el estado mediante el delegado
    }

    TextField(
        value = rememberedText, // Al obtener el valor se lee el estado mediante el delegado
        onValueChange = onTextChange
    )
}

/**
 * Utilizamos desestructuración
 */
@Composable
fun MyTextField3() {
    // desestructuramos las propiedades del objeto MutableState en dos variables
    // la primera de tipo String contiene el valor memoriazado del estado
    // la sefunda de tipo función recibe un String y lo usa para actualizar el estado
    val (rememberedText, setRememberedText) = remember { mutableStateOf("") }

    val onTextChange = { newText: String ->
        setRememberedText(newText) // Se usa el objeto función para actualizar el estado
    }

    TextField(
        value = rememberedText, // Se usa el valor actual del estado almacenado en el String
        onValueChange = onTextChange
    )
}



@Preview
@Composable
fun RecompositionDemoScreenPreview() {
    PMDMComposeTheme {
        RecompositionDemoScreen()
    }
}