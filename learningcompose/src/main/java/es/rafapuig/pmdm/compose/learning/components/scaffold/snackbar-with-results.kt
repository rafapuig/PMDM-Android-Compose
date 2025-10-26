package es.rafapuig.pmdm.compose.learning.components.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Preview(showSystemUi = true)
@Composable
fun SnackbarWithResultsDemo() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var text by remember { mutableStateOf("Aqui verás el resultado") }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Button(onClick = {
                /**
                 * Función launch que crea un corrutina
                 */
                scope.launch {
                    /**
                     * showSnackbar es una función suspendida,
                     * por lo que se debe llamar desde una corrutina.
                     */
                    val result: SnackbarResult = snackbarHostState.showSnackbar(
                        message = "Message",
                        actionLabel = "Action",
                        withDismissAction = true, // Muestra un aspa de cierre
                    )

                    /**
                     * SnackBarResult es un enumerado
                     */

                    when (result) {
                        /**
                         * Cuando se hace click en el botón de acción
                         */
                        SnackbarResult.ActionPerformed -> {
                            text = "Action Performed"
                        }
                        /**
                         * Cuando se termina el tiempo de duración del SnackBar
                         * (o se pulsa el aspa de cancelar en su caso)
                         */
                        SnackbarResult.Dismissed -> {
                            text = "Action Dismissed"
                        }
                    }
                }

            }) {
                Text(text = "Mostrar Snackbar", fontSize = 20.sp)
            }
            Text(text = text, fontSize = 20.sp)
        }
    }
}