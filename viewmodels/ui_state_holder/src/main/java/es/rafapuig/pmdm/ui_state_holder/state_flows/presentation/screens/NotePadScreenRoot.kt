package es.rafapuig.pmdm.ui_state_holder.state_flows.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.ui_state_holder.core.presentation.screens.NotePadScreen
import es.rafapuig.pmdm.ui_state_holder.state_flows.presentation.NotePadViewModel

/**
 * El viewModel en este ejemplo, mantiene el estado de la UI
 * mediante el uso de StateFlows
 * Para transformar un StateFlow en un estado observable de Compose se utiliza
 * la función collectAsStateWithLifecycle()
 */
@Composable
fun NotePadScreenRoot(
    viewModel: NotePadViewModel = viewModel()
) {
    val text by viewModel.text.collectAsStateWithLifecycle()

    NotePadScreen(
        text = text,
        onTextChange = viewModel::onTextChange
    )
}