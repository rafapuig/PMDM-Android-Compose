package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.mvi_viewmodel.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.mvi_viewmodel.CounterViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme


@Composable
fun MVICounterScreenRoot(viewModel: CounterViewModel = viewModel()) {

    /**
     * Los StateFlow se pueden recolectar y actualizar un state de compose
     * mediante la función collectAsStateWithLifecycle()
     */

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MVICounterScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )

}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MVICounterScreenRootPreview() {
    PMDMComposeTheme {
        /**
         * La función composable viewModel devuelve el ViewModel
         * que sobrevive a los cambios de configuración de la Activity
         * que alberga el composable screen
         * Esta función está definida en la libreria:
         * "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
         */
        val viewModel = viewModel<CounterViewModel>()

        MVICounterScreenRoot(viewModel)
    }
}