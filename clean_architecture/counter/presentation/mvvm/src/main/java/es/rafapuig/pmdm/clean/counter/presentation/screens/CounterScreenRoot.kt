package es.rafapuig.pmdm.clean.counter.presentation.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.clean.counter.presentation.CounterViewModel
import es.rafapuig.pmdm.clean.counter.presentation.ui.theme.CleanCounterTheme

@Composable
fun CounterScreenRoot(
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
    CleanCounterTheme {
        CounterScreenRoot()
    }
}