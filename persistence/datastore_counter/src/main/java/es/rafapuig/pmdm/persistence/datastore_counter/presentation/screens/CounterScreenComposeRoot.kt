package es.rafapuig.pmdm.persistence.datastore_counter.presentation.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.persistence.datastore_counter.data.CounterDataStore
import es.rafapuig.pmdm.persistence.datastore_counter.data.counterDataStore
import es.rafapuig.pmdm.persistence.datastore_counter.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CounterScreenComposeRoot() {

    val context = LocalContext.current

    val dataStore = context.counterDataStore
    val counterDataStore = CounterDataStore(dataStore)


    val counter by counterDataStore.counterFlow
        .collectAsStateWithLifecycle(initialValue = 0)

    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    fun slowAction(action: suspend () -> Unit) {
        scope.launch {
            isLoading = true
            delay(500)
            action()
            isLoading = false
        }
    }

    CounterScreen(
        counter = counter,
        isLoading = isLoading,
        onIncrement = { slowAction { counterDataStore.increment() } },
        onDecrement = { slowAction { counterDataStore.decrement() } },
        onReset = { slowAction { counterDataStore.reset() } }
    )

}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CounterScreenComposeRootPreview() {
    PMDMComposeTheme {
        CounterScreenComposeRoot()
    }
}