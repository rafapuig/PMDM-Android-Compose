package es.rafapuig.pmdm.clean.authentication.auth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.clean.authentication.auth.presentation.login.LoginRoute
import es.rafapuig.pmdm.clean.authentication.auth.presentation.register.RegisterRoute
import es.rafapuig.pmdm.clean.authentication.main.presentation.MainScreen

@Composable
fun NavigationRoot() {
    // Navegación global con Navigation 3
    val backStack = rememberNavBackStack(LoginKey)

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
                MainScreen(
                    onLogout = {
                        backStack.clear()
                        backStack.add(LoginKey)
                    }
                )
            }

        }
    )
}
