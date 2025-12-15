package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.no_viewmodel.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CounterScreenRoot() {
    /**
     * Con rememberSaveable nos aseguramos que no se pierde el estado
     * dinámico de la UI aunque el SO mate la app por estar en segundo plano
     * y haga falta memoria
     * (Prueba a matar la aplicación desde el LogCat: Click Derecho + Kill)
     */

    var counter by rememberSaveable { mutableIntStateOf(0) }
    var isLoading by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    fun slowAction(action: () -> Unit) {
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
        onIncrement = { slowAction { counter++ } },
        onDecrement = { slowAction { counter-- } },
        onReset = { slowAction { counter = 0 } }
    )

}

@Preview(showSystemUi = true)
@Composable
fun CounterScreenRootPreview() {
    PMDMComposeTheme {
        CounterScreenRoot()
    }
}