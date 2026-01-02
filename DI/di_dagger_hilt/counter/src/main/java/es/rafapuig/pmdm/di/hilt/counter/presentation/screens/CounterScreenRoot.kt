package es.rafapuig.pmdm.di.hilt.counter.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.di.hilt.counter.presentation.CounterViewModel

@Composable
fun CounterScreenRoot(
    viewModel: CounterViewModel = viewModel() /* hiltViewModel() */
) {

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    MVICounterScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        events = viewModel.events
    )
}