package es.rafapuig.pmdm.compose.learning.concepts.`state-hoisting`

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.R

/**
 * Alternar (toggling) la visiblidad de un elemento de la UI
 */

@Preview
@Composable
fun TogglingExample() {

    /**
     * La variable showImage accede al estado que controla si es visible o no una imagen
     */
    var showImage by remember { mutableStateOf(false) }

    Column {
        Button(onClick = { showImage = !showImage }) {
            val text = if(showImage) "Ocultar" else "Mostrar"
            Text(text = text)
        }
        if(showImage) {
            Image(
                painter = painterResource(id = R.drawable.levante_ud_logo),
                contentDescription = null
            )
        }
    }

}