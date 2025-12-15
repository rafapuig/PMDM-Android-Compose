package es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_state_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import es.rafapuig.pmdm.compose.viewmodel.counter.mvi_one_off_events_as_state_viewmodel.screens.MVICounterScreenRoot
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme

class CounterActivity : ComponentActivity() {

    /**
     * La función viewModels devuelve el ViewModel de la Activity
     * Devuelve un Lazy<CounterViewModel> para que se cree solo cuando se use por primera vez
     * Usa una factoria predeterminada, puesto que el ViewModel no tiene argumentos (dependencias)
     */
    val viewModel : CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMComposeTheme {
                /**
                 * Podemos parar el ViewModel al composable Screen
                 */
                MVICounterScreenRoot(viewModel)

                /**
                 * o dejar que use el ViewModel por defecto declarado en su parametro
                 * viewModel
                 */
                //MVICounterScreenRoot()
            }
        }
    }
}