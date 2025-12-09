package es.rafapuig.pmdm.compose.learning.side_effects.effecthandlers.rememberUpdateState

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SearchListExample(modifier: Modifier = Modifier) {

    println("Componiendo SearchListExample...")

    var toggle by remember { mutableStateOf(false) }

    var search by remember { mutableStateOf("") }

    val names = listOf("Juan", "Lucía", "Mario", "Carmen", "Luis")


    /**
     * Este calculo se realizará en cada recomposición
     * Aunque solo se debe calcular cuando search haya cambiado
     */
    val filteredNamesWrong = run {
        println("Recalculando filtro con $search")
        if (search.isBlank()) names
        else names.filter { it.contains(search.trim(), ignoreCase = true) }
    }


    /**
     * Se recalcula solo cuando 'search' cambia
     * al usar remember con una key del valor del que depende el calculo
     */
    val filteredNamesWithRememberKey = remember(search) {
        println("Recalculando filtro con $search (remember + key)")
        if (search.isBlank()) names
        else names.filter { it.contains(search.trim(), ignoreCase = true) }
    }

    /**
     * Se recalcula solo cuando 'search' cambia
     * al usar derivedStateOf junto con remember
     */
    val filteredNames by remember {
        derivedStateOf {
            println("Recalculando filtro con $search (derivedStateOf)")
            if (search.isBlank()) names
            else names.filter { it.contains(search.trim(), ignoreCase = true) }
        }
    }


    Column(
        modifier = modifier
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = search,
            onValueChange = { search = it },
            label = { Text("Buscar") }
        )

        filteredNames.forEach { name ->
            Text(text = name)
        }

        Button(onClick = { toggle = !toggle }) {
            Text(text = "Recomponer")
        }
        /** Leemos el estado para que sea observado por el Text y produzca recomposicion */
        Text(text = "Estado de 'toggle': $toggle")
    }
}


@Preview(showBackground = true)
@Composable
fun SearchListExamplePreview() {
    Scaffold { innerPadding ->
        SearchListExample(modifier = Modifier.padding(innerPadding))
    }
}
