package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

suspend fun performSlowTask() {
    Log.i("PMDM","Realizando tarea lenta...")
    Log.i("PMDM",currentCoroutineContext().toString())
    delay(5.seconds)
    Log.i("PMDM","Tarea lenta completada.")
}

@Preview(showBackground = true)
@Composable
fun CoroutineDemo() {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Button(
            onClick = {
                coroutineScope.launch {
                    // Realizar una tarea asíncrona
                    performSlowTask()
                }
            }
        ) {
            Text("Realizar Tarea Lenta")
        }
    }

}