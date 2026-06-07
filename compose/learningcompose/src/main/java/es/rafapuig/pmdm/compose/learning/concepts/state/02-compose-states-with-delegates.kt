package es.rafapuig.pmdm.compose.learning.concepts.state.delegation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

/**
 * Para facilitar el uso de los estados de compose
 * estos se pueden delegar en una propiedad
 * mediante el uso de by
 * De esta manera, establecer el valor de la propiedad equivale a modificar el valor
 * del estado delegado en la propiedad
 * y para leer estado se accede directamente a la propiedad
 *
 * Si declaramos la propiedad de delegación con val
 * solamente podremos leer el estado
 * (Habrá que importar la función androidx.compose.runtime.getValue)
 *
 * Para modificarlo declaramos la propiedad con var
 * (Habrá que importar la función androidx.compose.runtime.setValue)
 */

val passwordState = mutableStateOf("")
var password by passwordState

@Preview
@Composable
fun MyPasswordTextField() {
    TextField(
        value = password, // usamos directamente la propiedad en que hemos delegado (leer)
        onValueChange = { password = it } // Usamos la propiedad para modificar estado (establecer)
    )
}

/**
 * Podemos declarar directamente la propiedad y delegarle el estado
 */
var isLoading by mutableStateOf(false)

@Preview
@Composable
fun MyIsLoadingViewer() {
    Column {
        Switch(
            checked = isLoading,
            onCheckedChange = { isLoading = it } // modificación del estado mediante set de la prop
        )
        // lectura del valor del estado mediante acceso a la propiedad delegado
        if (isLoading) CircularProgressIndicator()
    }
}