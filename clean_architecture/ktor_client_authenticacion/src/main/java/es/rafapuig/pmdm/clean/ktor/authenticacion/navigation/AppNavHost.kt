package es.rafapuig.pmdm.clean.ktor.authenticacion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.presentation.SessionEvent
import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.presentation.SessionManager
import org.koin.compose.koinInject

@Composable
fun AppNavHost(
    startDestination: AppNavKey,
    sessionManager: SessionManager = koinInject()
) {
    val backStack = remember(startDestination) { mutableStateListOf(startDestination) }

    LaunchedEffect(Unit) {
        sessionManager.events.collect {
            if (it is SessionEvent.LoggedOut) {
                backStack.clear()
                backStack.add(AppNavKey.Login)
            }
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { if (backStack.size > 1) { backStack.removeLast(); true } else false }
    ) { entry ->
        when (entry.key) {
            AppNavKey.Login -> AuthNavGraph(entry) { backStack.clear(); backStack.add(AppNavKey.Home) }
            AppNavKey.Register -> AuthNavGraph(entry) { backStack.clear(); backStack.add(AppNavKey.Home) }
            AppNavKey.Home -> HomeNavGraph(entry, sessionManager)
            AppNavKey.Profile -> ProfileNavGraph(entry)
        }
    }
}

