package es.rafapuig.pmdm.resources

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GreetingFromRawResources () {

    val context = LocalContext.current

    /** Obtener el Resources a partir del contexto se considera deprecado */
    val text = context.resources.openRawResource(R.raw.greeting)

    /** Es mejor obtenerlo directamente mediante el proveedor local de composición LocalResources */
    val resources = LocalResources.current
    val text2 = resources.openRawResource(R.raw.greeting)

    Column {
        Text(
            text = text.bufferedReader().use {
                it.readText()
            }
        )
        Text(
            text = text2.bufferedReader().use {
                it.readText()
            }
        )
    }

}

@Preview(showBackground = true)
 @Composable
fun GreetingFromRawResourcesPreview() {
    GreetingFromRawResources()
}