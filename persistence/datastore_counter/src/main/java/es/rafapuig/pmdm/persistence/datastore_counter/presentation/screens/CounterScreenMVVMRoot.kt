package es.rafapuig.pmdm.persistence.datastore_counter.presentation.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.datastore_counter.presentation.CounterViewModel
import es.rafapuig.pmdm.persistence.datastore_counter.ui.theme.PMDMComposeTheme


@Composable
fun CounterScreenMVVMRoot(
    viewModel: CounterViewModel = viewModel(factory = CounterViewModel.Factory)
) {

    val counter by viewModel.counterFlow
        .collectAsStateWithLifecycle()

    val isLoading by viewModel.isLoading
        .collectAsStateWithLifecycle()


    CounterScreen(
        counter = counter,
        isLoading = isLoading,
        onIncrement = { viewModel.increment() },
        onDecrement = { viewModel.decrement() },
        onReset = { viewModel.reset() }
    )
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CounterScreenRootPreview() {
    PMDMComposeTheme {
        CounterScreenMVVMRoot()
    }
}