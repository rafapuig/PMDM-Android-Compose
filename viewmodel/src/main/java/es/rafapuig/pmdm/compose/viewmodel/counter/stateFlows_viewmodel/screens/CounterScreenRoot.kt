package es.rafapuig.pmdm.compose.viewmodel.counter.stateFlows_viewmodel.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.counter.stateFlows_viewmodel.CounterViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme


@Composable
fun CounterScreenRoot(viewModel: CounterViewModel = viewModel()) {

    /**
     * Los StateFlow se pueden recolectar y actualizar un state de compose
     * mediante la funcion collectAsStateWithLifecycle()
     */

    val counter by viewModel.counter.collectAsStateWithLifecycle()

    /**
     * Si no tuvieramos collectAsStateWithLifecycle()
     * tendríamos que escribir todo esto a bajo nivel
     */
    val lifecycleOwner = LocalLifecycleOwner.current
    val isLoading by produceState(
        initialValue = viewModel.isLoading.value) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.isLoading.collect { value = it }
        }
    }

    CounterScreen(
        counter = counter,
        isLoading = isLoading,
        onIncrement = viewModel::increment,
        onDecrement = viewModel::decrement,
        onReset = viewModel::reset
    )

}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CounterScreenRootPreview() {
    PMDMComposeTheme {
        /**
         * La función composable viewModel devuelve el ViewModel
         * que sobrevive a los cambios de configuración de la Activity
         * que alberga el composable screen
         * Esta función está definida en la libreria:
         * "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
         */
        val viewModel = viewModel<CounterViewModel>()

        CounterScreenRoot(viewModel)
    }
}