package es.rafapuig.pmdm.compose.viewmodel.counter.states_viewmodel.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.counter.states_viewmodel.CounterViewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme


@Composable
fun CounterScreenRoot(viewModel: CounterViewModel = viewModel()) {

    /*var counter by remember { mutableIntStateOf(0) }
       var isLoading by remember { mutableStateOf(false) }

       val scope = rememberCoroutineScope()

       fun slowAction(action: () -> Unit) {
           scope.launch {
               isLoading = true
               delay(500)
               action()
               isLoading = false
           }
       }*/

    CounterScreen(
        counter = viewModel.counter,
        isLoading = viewModel.isLoading,
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