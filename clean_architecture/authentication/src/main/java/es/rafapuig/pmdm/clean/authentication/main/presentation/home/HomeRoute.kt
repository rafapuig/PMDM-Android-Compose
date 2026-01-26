package es.rafapuig.pmdm.clean.authentication.main.presentation.home

import androidx.compose.runtime.Composable
import es.rafapuig.pmdm.clean.authentication.core.presentation.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    onLoggedOut: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {

    viewModel.events.ObserveAsEvents { event ->
        when (event) {
            HomeUiEvent.LoggedOut -> onLoggedOut()
        }
    }

    HomeScreen(
        onLogout = viewModel::onLogout
    )
}