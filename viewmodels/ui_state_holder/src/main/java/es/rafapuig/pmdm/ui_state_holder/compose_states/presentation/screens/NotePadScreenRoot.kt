package es.rafapuig.pmdm.ui_state_holder.compose_states.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.ui_state_holder.compose_states.presentation.NotePadViewModel
import es.rafapuig.pmdm.ui_state_holder.core.presentation.screens.NotePadScreen


@Composable
fun NotePadScreenRoot(
    viewModel: NotePadViewModel = viewModel()
) {
    NotePadScreen(
        text = viewModel.text,
        onTextChange = viewModel::onTextChange
    )
}