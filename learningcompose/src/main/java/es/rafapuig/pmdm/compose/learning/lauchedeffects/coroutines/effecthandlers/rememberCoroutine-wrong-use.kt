package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun ComposableCannotLaunchCoroutine() {

    val coroutineScope = rememberCoroutineScope()

    /**
     * Esto es un ejemplo de lo que no se deberia hacer
     * dado que con cada recomposición se lanzaría la corrutina
     */
    coroutineScope.launch {
        /**
         * Observa el LogCat y verás como se lanza la corrutina
         * con cada recomposición (escribe en el textfield o rota el móvil)
         */
        performSlowTask()
    }

    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = text,
            onValueChange = {text = it}
        )
    }
}