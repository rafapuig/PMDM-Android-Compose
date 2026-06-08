package es.rafapuig.pmdm.compose.learning.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModifiersDemoScreen(modifier: Modifier = Modifier) {

    /**
     * Creamos un modificador vacío por defecto (sin configuraciones)
     * (en realidad solo asignamos la referencia al companion object
     * de la interface Modifier)
     */
    var myModifier: Modifier = Modifier

    /**
     * Configuramos el modificador para que añada un padding de 10dp
     * a todos los lados del composable al que se aplique
     * (en realidad estamos creando un nuevo modificador clonado del receiver
     * usando su configuración más la que aplica la función padding)
     */
    myModifier = myModifier.padding(all = 10.dp)

    /**
     * Ahora añadimos un borde de 2dp de color negro
     */
    myModifier = myModifier.border(width = 2.dp, color = Color.Black)

    /**
     * Es como si estuviéramos usando el patrón decorador
     * con cada función de extension estamos creando un nuevo modificador
     * a partir de crear una copia del modificador receptor decorado según
     * la función de extensión que se esté llamando
     */

    /**
     * Como las funciones de decoración devuelven a su vez el modificador
     * decorado podemos encadenar las llamadas para aplicar multiples
     * decoradores (configuraciones)
     */

    val modifierWithPaddingAndBorder =
        Modifier.padding(all = 10.dp).border(width = 2.dp, color = Color.Black)


    /**
     * En esta función composable hemos recibido un modificador
     * en el parámetro modifier
     * Podemos usarlo como partida para ir aplicándole configuraciones adicionales
     */

    val modifierWithExtraConfig = modifier
        .border(width = 2.dp, color = Color.Black)
        .padding(all = 10.dp)


    /**
     * Una vez tenemos el modifier configurado podemos pasarlo
     * a una función composable
     * si esta declara un parámetro de tipo Modifier
     *
     * Si es así, este parámetro deberá ser el primer parámetro
     * que tenga definido un valor por defecto
     * dentro de la lista de parámetros de dicha función composable
     * (lo que nos permite pasar el argumento por posición o por nombre)
     */
    Text(
        "Hola Modifiers",
        modifier = modifierWithExtraConfig, // o simplemente modifierWithExtraConfig
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
fun ModifiersDemoScreenPreview() {
    ModifiersDemoScreen()
}