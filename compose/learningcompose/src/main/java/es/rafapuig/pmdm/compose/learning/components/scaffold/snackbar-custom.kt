package es.rafapuig.pmdm.compose.learning.components.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
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
fun CustomSnackbarDemo() {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val customSnackBar = @Composable { snackbarData : SnackbarData ->
        Snackbar(snackbarData,
            actionOnNewLine = true,
            shape = MaterialTheme.shapes.medium,
            containerColor = SnackbarDefaults.color, // MaterialTheme.colorScheme.inverseSurface,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface,
            actionContentColor = MaterialTheme.colorScheme.inversePrimary,
            dismissActionContentColor = MaterialTheme.colorScheme.inverseOnSurface
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState, snackbar = customSnackBar) }
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
                            message = "Mensaje del componente snackbar personalizado\nOtra linea",
                            actionLabel = "Acción a realizar",
                            withDismissAction = true, // Muestra un aspa de cierre
                            duration = SnackbarDuration.Short // Duración del mensaje
                        )
                    }
                }
        )
    }
}