package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.navigation.LoginNavKey

@Composable
fun AuthNavGraph(
    navEntry: NavEntry,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    when (val key = navEntry.key) {
        is LoginNavKey -> {
            val viewModel: LoginViewModel = navEntry.viewModel(
                decorator = rememberViewModelStoreNavEntryDecorator()
            )
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = onLoginSuccess,
                onRegisterClick = onRegisterClick
            )
        }
        is RegisterNavKey -> {
            val viewModel: RegisterViewModel = navEntry.viewModel(
                decorator = rememberViewModelStoreNavEntryDecorator()
            )
            RegisterScreen(
                viewModel = viewModel,
                onRegisterSuccess = onLoginSuccess
            )
        }
    }
}
