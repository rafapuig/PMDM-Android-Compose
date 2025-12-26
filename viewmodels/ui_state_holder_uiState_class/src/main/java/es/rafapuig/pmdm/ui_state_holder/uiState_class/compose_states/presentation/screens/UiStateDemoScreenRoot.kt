package es.rafapuig.pmdm.ui_state_holder.uiState_class.compose_states.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.ui_state_holder.uiState_class.compose_states.presentation.UiStateDemoViewModel
import es.rafapuig.pmdm.ui_state_holder.uiState_class.core.presentation.screens.UiStateDemoScreen


@Composable
fun UiStateDemoScreenRoot(
    viewModel: UiStateDemoViewModel = viewModel()
) {
    UiStateDemoScreen(
        // Pasamos todos los elementos del estado en un solo objeto UiState
        uiState = viewModel.uiState,
        onNameChange = viewModel::onNameChange,
        onUpperCaseChange = viewModel::onUpperCaseChange
    )

}