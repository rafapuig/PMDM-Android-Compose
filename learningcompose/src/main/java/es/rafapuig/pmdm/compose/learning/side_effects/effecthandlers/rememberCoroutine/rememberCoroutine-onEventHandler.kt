package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberCoroutine

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.time.Duration.Companion.seconds


@Preview(showBackground = true)
@Composable
fun CoroutineDemo() {

    val coroutineScope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    suspend fun performSlowTask() {
        Log.i("PMDM", "Realizando tarea lenta...")
        snackbarHostState.showSnackbar("Realizando tarea lenta...")

        Log.i("PMDM", currentCoroutineContext().toString())

        delay(2.seconds)

        Log.i("PMDM", "Tarea lenta completada.")
        snackbarHostState.showSnackbar("Tarea lenta completada.")
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    /**
                     * Es el lugar correcto para lanzar una corrutina con el
                     * scope de la composición.
                     */
                    coroutineScope.launch(Dispatchers.IO) {
                        // Realizar una tarea asíncrona
                        performSlowTask()
                    }

                }
            ) {
                Text("Iniciar Tarea Lenta")
            }
        }
    }
}