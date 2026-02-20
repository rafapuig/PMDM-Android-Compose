package es.rafapuig.pmdm.resources

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

/**
 * Para crear el archivo greeting.txt como un asset
 * tenemos que hacer clic botón secundario en el nombre del módulo
 * y elegir New -> Directory
 * src/main/assets
 */

@Composable
fun GreetingFromAssets() {

    val context = LocalContext.current

    /**
     * La propiedad assets devuelve un AssetsManager
     * El método use es el equivalente al try-with-resources de Java
     * que cierra el recurso al salir del bloque
     * y devuelve el resultado del bloque
     */

    val text = context.assets
        .open("greeting.txt")
        .bufferedReader()
        .use {
            it.readText()
        }

    Text(text = text)


}

@Preview(showBackground = true)
@Composable
fun GreetingFromAssetsPreview() {
    GreetingFromAssets()
}