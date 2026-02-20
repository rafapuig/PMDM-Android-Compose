package es.rafapuig.pmdm.resources

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GreetingFromStringResource() {

    /** Texto "hardcodeado"
     * No es recomendado "hardcodear" los textos en el código fuente
     * Es mejor usar strings.xml para definirlos en un fichero de recursos
     * De esta manera se pueden localizar según el idioma del dispositivo
     * en que se ejecuta la aplicación
     */
    /** Para "extraer" un texto "harcodeado" a strings.xml, selecciónalo
     * y pulsa sobre la bombilla que aparece
     * elige la opción Extract String Resource
     */
    val hardcodedText = "Hola Android!!!" // <- prueba con este texto harcodeado

    /**
     * La función composable stringResource accede a strings.xml y obtiene
     * mediante el ID del recurso la cadena de texto correspondiente
     * El ID del recurso lo obtenemos normalmente mediante la clase R
     */
    val text = stringResource(R.string.greeting)


    val context = LocalContext.current

    /**
     * Es posible obtener el texto mediante el método getString de la clase Context
     * Pero dentro de una función composable no se considera una práctica correcta
     */
    val textFromContext = context.getString(R.string.greeting)

    /** Podemos obtenerlo también mediante el proveedor local de composición LocalResources */
    val resources = LocalResources.current
    val textFromResources = resources.getString(R.string.greeting)

    Column {
        Text(
            text = hardcodedText
        )
        Text(
            text = text
        )
        Text(
            text = textFromContext
        )
        Text(
            text = textFromResources
        )
    }
}

@Preview(showBackground = true)
 @Composable
fun GreetingFromStringResourcesPreview() {
    GreetingFromStringResource()
}