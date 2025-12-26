package es.rafapuig.pmdm.ui_state_holder.uiState_class.state_flows.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation.screens.UiStateDemoScreen
import es.rafapuig.pmdm.ui_state_holder.uiState_class.state_flows.presentation.UiStateDemoViewModel


@Composable
fun UiStateDemoScreenRoot(
    viewModel: UiStateDemoViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UiStateDemoScreen(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onUpperCaseChange = viewModel::onUpperCaseChange
    )
}