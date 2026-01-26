package es.rafapuig.pmdm.clean.ktor.authenticacion.features.auth.presentation


import androidx.compose.runtime.Composable
import com.slack.circuit.foundation.rememberViewModelStoreNavEntryDecorator
import com.slack.circuit.runtime.NavEntry

@Composable
fun RegisterNavGraph(navEntry: NavEntry, onRegisterSuccess: () -> Unit) {
    val viewModel: RegisterViewModel = navEntry.viewModel(decorator = rememberViewModelStoreNavEntryDecorator())
    RegisterScreen(viewModel, onRegisterSuccess)
}
