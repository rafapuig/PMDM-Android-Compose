package es.rafapuig.pmdm.compose.learning.side_effects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


private var recompositions = 0

@Preview
@Composable
fun RecompositionDemo() {

    Scaffold { innerPadding ->

        println("Componiendo RecompositionDemo...")

        var toggle by remember { mutableStateOf(false) }
        var search by remember { mutableStateOf("") }

        /*val mode by remember {
            derivedStateOf {
                println("Calculando mode...")
                if (toggle) "Dark" else "Light"
            }
        }*/

        val mode = run {
            println("Calculando mode...")
            if (toggle) "Dark" else "Light"
        }


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = search,
                onValueChange = { search = it }
            )
            Button(onClick = { toggle = !toggle }) {
                Text("Toggle")
            }
            // LEEMOS EL ESTADO → provoca recomposición real
            Text("Toggle state: $toggle")
            // LEEMOS EL ESTADO → provoca recomposición real
            Text("Mode: $mode")

            Text("Recompositions: $recompositions")

        }

        SideEffect { recompositions++ }
        println("Recompositions: $recompositions")

    }
}
