package es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.mvi_one_off_events_as_state_viewmodel.screens

import android.content.res.Configuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.saving_ui_state.counter.mvi_one_off_events_as_state_viewmodel.CounterViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme


@Composable
fun MVICounterScreenRoot(viewModel: CounterViewModel = viewModel()) {

    /**
     * Los StateFlow se pueden recolectar y actualizar un state de compose
     * mediante la función collectAsStateWithLifecycle()
     */

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.message) {
        uiState.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            /**
             * Si nos olvidamos de llamar a esta linea
             * no se dará el mensaje por consumido en la UI
             * y
             * 1) Cuando haya un cambio de configuración volveremos a ver el
             * mensaje
             * (prueba comentar la linea y rotar la pantalla despues de ver el mensaje
             * verás que vuelve a aparecer)
             * 2) Otro evento con el mismo mensaje ya no se vuelve a mostrar de nuevo
             * (porque el LauchedEffect no se ejecuta por ser el mismo mensaje que antes
             * no ha cambiado el valor de la key)
             * (prueba a resetar por segunda vez el contador y
             * verás que ya no se muestra el Snackbar)
             */
            viewModel.onMessageShown()
        }
    }

    MVICounterScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState
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