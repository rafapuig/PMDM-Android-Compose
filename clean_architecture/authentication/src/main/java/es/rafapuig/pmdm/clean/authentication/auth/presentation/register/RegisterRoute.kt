package es.rafapuig.pmdm.clean.authentication.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterRoute(
    onRegisterSuccess: () -> Unit,
    onBack: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterScreen(
        state = state,
        onRegisterClick = viewModel::register,
        onSuccess = onRegisterSuccess,
        onBack = onBack
    )
}
