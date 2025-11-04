package es.rafapuig.pmdm.compose.exercises.navigation.exercise3

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.compose.exercises.navigation.exercise3.screens.HomeScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise3.screens.WelcomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenKey : NavKey

@Serializable
data class WelcomeScreenKey(val username: String) : NavKey


@Composable
fun NavigationRoot() {

    val navBackStack = rememberNavBackStack(HomeScreenKey)

    NavDisplay(
        backStack = navBackStack,
        onBack = { if (navBackStack.size > 1) navBackStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<HomeScreenKey> { key ->
                HomeScreen(
                    onRegister = { username ->
                        navBackStack.add(WelcomeScreenKey(username))
                    }
                )
            }
            entry<WelcomeScreenKey> { key ->
                WelcomeScreen(
                    username = key.username,
                    onBack = {
                        navBackStack.removeLastOrNull()
                    }
                )
            }
        }
    )


}

@Preview
@Composable
fun NavigationRootPreview() {
    NavigationRoot()
}