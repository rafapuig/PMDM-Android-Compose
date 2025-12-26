package es.rafapuig.pmdm.clean.subscribers.viewmodel_logic.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.clean.subscribers.core.presentation.screens.SubscribersScreen
import es.rafapuig.pmdm.clean.subscribers.viewmodel_logic.presentation.SubscribersViewModel

@Composable
fun SubscribersScreenRoot(viewModel: SubscribersViewModel = viewModel()) {

    val subscribers by viewModel.subscribersState.collectAsStateWithLifecycle()

    SubscribersScreen(subscribers)

}