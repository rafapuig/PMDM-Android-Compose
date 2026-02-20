package es.rafapuig.pmdm.clean.ktor.authenticacion.features.home.presentation


import androidx.compose.runtime.Composable
import com.slack.circuit.foundation.rememberViewModelStoreNavEntryDecorator
import com.slack.circuit.runtime.NavEntry

@Composable
fun HomeNavGraph(navEntry: NavEntry, sessionManager: SessionManager) {
    val viewModel: HomeViewModel = navEntry.viewModel(decorator = rememberViewModelStoreNavEntryDecorator())
    HomeScreen(viewModel, onProfileClick = { /* navegar a Profile */ })
}
