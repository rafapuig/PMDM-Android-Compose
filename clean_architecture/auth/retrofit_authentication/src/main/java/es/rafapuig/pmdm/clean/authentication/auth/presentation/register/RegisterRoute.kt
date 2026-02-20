package es.rafapuig.pmdm.clean.authentication.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.clean.authentication.core.presentation.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterRoute(
    onRegisterSuccess: () -> Unit,
    onBack: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.events.ObserveAsEvents { event ->
        when(event) {
            RegisterUiEvent.RegisterSuccess -> onRegisterSuccess()
        }
    }

    RegisterScreen(
        state = state,
        onRegisterClick = viewModel::register,
        onBack = onBack
    )
}
