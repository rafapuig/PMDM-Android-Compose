package es.rafapuig.pmdm.di.counter.hilt.navm.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.di.counter.common.presentation.screens.MVICounterScreen

@Composable
fun CounterScreenRoot(viewModel: CounterViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MVICounterScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        events = viewModel.events
    )
}