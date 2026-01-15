package es.rafapuig.pmdm.di.users.koin.compose.presentation.greeting

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

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