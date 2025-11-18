package es.rafapuig.pmdm.compose.learning.lauchedeffects.coroutines

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

val cities = listOf(
    "Madrid",
    "London",
    "Paris",
    "Rome",
    "Berlin",
    "Amsterdam",
    "Vienna",
    "Prague"
)


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LaunchedEffectDebounceDemo(
    onSearch: (String) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }

    var filteredCities by remember { mutableStateOf(emptyList<String>()) }

    var isSearching by remember { mutableStateOf(false) }

    LaunchedEffect(query) {
        //onSearch(query)
        delay(1000.milliseconds)
        isSearching = true
        delay(1000.milliseconds)
        isSearching = false
        filteredCities = cities.filter { it.contains(query, ignoreCase = true) }
        Log.d("PMDM", "filteredCities: $filteredCities")
    }

    PMDMComposeTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Búsqueda con debounce de 1 segundo") }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)

            ) {

                TextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Buscar...") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (isSearching) {
                    Text(text = "Buscando...")
                } else {
                    LazyColumn() {
                        items(filteredCities) { city ->
                            Text(text = city)
                        }
                    }
                }

            }
        }
    }
}