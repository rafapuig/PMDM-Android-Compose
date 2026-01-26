package es.rafapuig.pmdm.clean.ktor.authenticacion.features.profile.presentation


import androidx.compose.runtime.Composable
import com.slack.circuit.foundation.rememberViewModelStoreNavEntryDecorator
import com.slack.circuit.runtime.NavEntry

@Composable
fun ProfileNavGraph(navEntry: NavEntry) {
    val viewModel: ProfileViewModel = navEntry.viewModel(decorator = rememberViewModelStoreNavEntryDecorator())
    ProfileScreen(viewModel)
}
