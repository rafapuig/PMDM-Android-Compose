package es.rafapuig.pmdm.clean.subscribers.use_case_logic.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.clean.subscribers.core.presentation.screens.SubscribersScreen
import es.rafapuig.pmdm.clean.subscribers.use_case_logic.presentation.SubscribersViewModel

@Composable
fun SubscribersScreenRoot(
    viewModel: SubscribersViewModel =
        viewModel(factory = SubscribersViewModel.Factory)
) {

    val subscribers by viewModel.subscribers.collectAsStateWithLifecycle()

    SubscribersScreen(subscribers)
}