package es.rafapuig.pmdm.clean.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.clean.authentication.auth.navigation.SessionEvent
import es.rafapuig.pmdm.clean.authentication.auth.navigation.SessionManager
import es.rafapuig.pmdm.clean.authentication.auth.presentation.login.LoginRoute
import es.rafapuig.pmdm.clean.authentication.auth.presentation.register.RegisterRoute
import es.rafapuig.pmdm.clean.authentication.core.presentation.ObserveAsEvents
import es.rafapuig.pmdm.clean.authentication.main.presentation.home.HomeRoute
import org.koin.compose.koinInject


@Composable
fun NavigationRoot(
    startDestination: NavKey = LoginKey,
    sessionManager: SessionManager = koinInject()
) {
    // Navegación global con Navigation 3
    val backStack = rememberNavBackStack(startDestination)


    sessionManager.events.ObserveAsEvents { event ->
        when (event) {
            SessionEvent.LoggedOut -> {
                backStack.clear()
                backStack.add(LoginKey)
            }
        }
    }


    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<LoginKey> {
                LoginRoute(
                    onLoginSuccess = {
                        backStack.add(MainKey)
                    },
                    onNavigateToRegister = {
                        backStack.add(RegisterKey)
                    }
                )
            }

            entry<RegisterKey> {
                RegisterRoute(
                    onRegisterSuccess = {
                        backStack.add(MainKey)
                    },
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<MainKey> {
                HomeRoute(
                    onLoggedOut = {
                        backStack.clear()
                        backStack.add(LoginKey)
                    }
                )
            }

        }
    )
}
