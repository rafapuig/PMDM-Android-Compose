package es.rafapuig.pmdm.compose.learning.components.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

/**
 * https://m3.material.io/components/snackbar/overview
 */

@Preview(showSystemUi = true)
@Composable
fun SnackbarDemo() {
    /**
     * El snackbarHostState permite llamar al metodo showSnackbar para mostrar el snackbar
     */
    val snackbarHostState = remember { SnackbarHostState() }

    /**
     * La CoroutineScope permite lanzar corrutinas, para poder llamar a funciones suspendidas
     * (en este caso la función suspendida a llamar será showSnackbar
     */
    val scope = rememberCoroutineScope()

    /**
     * Creamos un @Composable que contendrá el SnackbarHost
     * El SnackbarHost hace uso del snackbarHostState para mostrar el snackbar
     * en un SnackBar que creará y le pasará un SnackBarData
     */
    val mySnackbarHost = @Composable {
        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = { snackbarData -> Snackbar(snackbarData) }
        )
    }

    Scaffold(
        snackbarHost = mySnackbarHost // Acoplamos el SnackbarHost al Scaffold
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .clickable {
                    /**
                     * Función launch que crea un corrutina
                     */
                    scope.launch {
                        /**
                         * showSnackbar es una función suspendida,
                         * por lo que se debe llamar desde una corrutina.
                         */
                        snackbarHostState.showSnackbar(
                            message = "Message",
                            actionLabel = "Action",
                            withDismissAction = true, // Muestra un aspa de cierre
                            duration = SnackbarDuration.Short // Duración del mensaje
                        )
                    }
                }
        )
    }
}