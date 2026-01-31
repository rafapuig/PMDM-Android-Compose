package es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

/**
 * El viewmodel se inyecta en Koin con koinViewModel()
 */
@Composable
fun GreetingUserScreenRoot(
    viewModel: GreetingUserViewModel = koinViewModel()
) {

    val uiState = viewModel.uiState
        .collectAsStateWithLifecycle()

    GreetingUserScreen(
        uiState = uiState.value,
        onAction = viewModel::onAction
    )

}