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
 * El nombre de la función debe empezar con mayúscula por convención
 * (Si la funcion devuelve un valor entonces si empieza en minúscula)
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
 * (Si una función no es composable no puede llamar a una composable dentro de ella)
 */
@Composable
fun ComposeFunction() {
    Text("Hola Compose")
}

/**
 * Pueden declarar parámetros
 */
@Composable
fun CustomText(text: String, fontSize: Int, color: Color) {
    Text(text = text, fontSize = fontSize.sp, color = color)
}

@Preview
@Composable
fun CustomTextPreview() {
    CustomText(text = "Hola Compose", fontSize = 24, color = Color.Yellow)
}

/**
 * Se puede incluir cualquier lógica de programación dentro de un composable
 * Condicionales, bucles, etc
 */
@Preview    // Probar el modo interactivo
@Composable
fun CustomSwitch() {
    // Declarar un estado mutable de tipo Boolean "checked" inicializado a false
    val checked = remember { mutableStateOf(false) }
    Column {
        // El Switch usa el valor del estado checked para determinar como tiene que renderizarse
        // si apagado o encendido
        // Se le pasa una función lambda que se ejecuta cuando se pulsa el Switch
        // y lo que hace es actualizar el valor del estado checked
        Switch(checked = checked.value, onCheckedChange = { checked.value = it })
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
        for (item in items) {
            Text(text = item, fontSize = 32.sp)
            HorizontalDivider(color = Color.Blue)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomListPreview() {
    val numberNames = listOf("Uno", "Dos", "Tres", "Cuatro", "Cinco")
    CustomList(items = numberNames)
}