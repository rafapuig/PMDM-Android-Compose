package es.rafapuig.pmdm.compose.learning.concepts

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * Un composable es una función anotada con @Composable
 * https://www.youtube.com/watch?v=fFLBCgoHHys
 *
 * Convenciones:
 * El nombre de la función debe empezar con mayúscula (PascalCase)
 * y utilizar nombres y adjetivos, pero no verbos
 * Fuente: https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-api-guidelines.md#naming-unit-composable-functions-as-entities
 *
 * Si la función devuelve un valor (no Unit) entonces debe empezar por minúscula
 * Fuente : https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-api-guidelines.md#naming-composable-functions-that-return-values
 */

@Composable
fun MyComposableFunction() {
}

@Composable
fun myComposableFunctionWithReturnValue(): Int {
    return 0
}


/**
 * Se puede llamar a otro composable dentro de la función composable
 * (Si una función no es composable
 * no puede llamar dentro de ella a una función composable)
 */
@Composable
fun ComposableFunction() {
    /**
     * La función Text es una función composable
     * podemos llamarla porque ComposableFunction está anotada como composable
     * Las funciones consideradas composables aparecen en color verde en el editor del IDE
     */
    Text("Hola Compose")
}

// Esta función es normal, (no esta anotada con @Composable) no es un Composable
fun noComposableFunction() {
    /* Errores de compilación no se puede llamar a una función Composable */
    // val value = myComposableFunctionWithReturnValue()
    // Text("Hola Compose")
}

/**
 * Pueden declarar parámetros
 */
@Composable
fun CustomText(text: String, fontSize: Int, color: Color) {
    Text(text = text, fontSize = fontSize.sp, color = color)
}

/**
 * Una función anotada con @Preview se puede previsualizar en el IDE
 */

@Preview
@Composable
fun CustomTextPreview() {
    CustomText(
        text = "Hola Compose",
        fontSize = 24,
        color = Color.Yellow
    )
}

/**
 * Se puede incluir cualquier lógica de programación dentro de un composable
 * condicionales, bucles, etc.
 * (siendo esta una de las principales ventajas respecto a Views en XML)
 */
@Preview    // Probar el modo interactivo
@Composable
fun CustomSwitch() {

    /**
     * Declarar un estado mutable de tipo Boolean "checked"
     * e inicializarlo a falso (durante la composición)
     * y recordado (remember) para no ser creado de nuevo en las recomposiciones
     * Ya veremos como escribir esto más resumido...
     * Los estados son observables por Compose y sus cambios provocan las recomposiciones
     */

    val checked = remember(
        calculation = {
            /* Crea un MutableState inicializado a false */
            mutableStateOf(false)
        }
    )

    Column {
        /**
         * El composable Switch utiliza el valor del estado "checked"
         * para determinar como tiene que renderizarse
         * si apagado o encendido
         *
         * En la llamada se le pasa una función lambda
         * - que se ejecuta cuando se pulsa el Switch
         * - y lo que hace es actualizar el valor del estado checked
         * con el nuevo valor de estado del Switch (it)
         */
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it } // lambda que actualiza el estado
        )
        // Lógica condicional en función del valor del estado checked
        if (checked.value) {
            Text(text = "Switch On")
        } else {
            Text(text = "Switch Off")
        }
    }
}


@Composable
fun CustomList(items: List<String>) {
    Column {
        /**
         * Una estructura de bucle for dentro de un composable
         */
        for (item in items) {
            Text(text = item, fontSize = 32.sp)
            HorizontalDivider(color = Color.Blue)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CustomListPreview() {

    val numberNames =
        listOf("Uno", "Dos", "Tres", "Cuatro", "Cinco")

    CustomList(items = numberNames)
}