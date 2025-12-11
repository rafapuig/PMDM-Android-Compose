@file:OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)

package es.rafapuig.pmdm.compose.exercises.side_effects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.exercises.ui.theme.ComposeExercisesTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

/**
 * Crea una pantalla que tenga un Texfield donde el usuario escribe
 * y debajo de este un Text que indique si el usuario está escribiendo...
 *
 * (Se considera que está escribiendo si ha pasado menos de 1 segundo desde que
 * se introdujo el último carácter)
 */

@Composable
fun IsTypingDemo(modifier: Modifier = Modifier) {

    var text by remember { mutableStateOf("") }
    var isTyping by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        snapshotFlow { text }
            .drop(1) // Quitamos el primero que el valor inicial del text
            .onEach {
                println("Escribió $it")
                isTyping = true
            }
            .debounce(1.seconds)
            .onEach {
                println("Ya ha pasado un segundo desde que escribió $it !!!")
                isTyping = false
            }
            .collect()
    }

    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight(.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {
        Text("Saber si el usuario está escribiendo")

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Escribe algo...") }
        )
        if (isTyping) {
            Text("El usuario está escribiendo...")
        }
    }
}



@Preview
@Composable
fun IsTypingDemoPreview() {
    ComposeExercisesTheme {
        Scaffold(
            topBar = { TopAppBar({ Text("IsTyping Demo") }) }
        )
        { innerPadding ->
            IsTypingDemo(Modifier.padding(innerPadding))
        }
    }
}