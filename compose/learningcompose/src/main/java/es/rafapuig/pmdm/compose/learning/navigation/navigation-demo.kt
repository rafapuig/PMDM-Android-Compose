package es.rafapuig.pmdm.compose.learning.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.compose.learning.navigation.screens.Home
import es.rafapuig.pmdm.compose.learning.navigation.screens.Profile
import es.rafapuig.pmdm.compose.learning.navigation.screens.Welcome
import es.rafapuig.pmdm.compose.learning.ui.theme.pastel.PastelTheme
import kotlinx.serialization.Serializable

/**
 * Declaración de las navigation keys
 */
@Serializable
data object HomeScreen : NavKey

@Serializable
data class WelcomeScreen(val name: String) : NavKey

@Serializable
data object ProfileScreen : NavKey


@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    // Creamos la pila de navegación y la inicializamos con la pantalla Home
    val backStack = rememberNavBackStack(HomeScreen)


    val onNavigation: (NavKey) -> Unit = {
        backStack.add(it)
    }

    val onClearBackStack: () -> Unit = {
        while (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() }, //Que pasa cuando pulsamos el botón de atrás
        modifier = modifier,
        entryProvider = entryProvider {
            entry<HomeScreen> {
                Home(onNavigation = onNavigation)
            }
            entry<WelcomeScreen> { key ->
                Welcome(name = key.name, onNavigation = onNavigation)
            }
            entry<ProfileScreen>(metadata = mapOf("extraDataKey" to "extraDataValue")) {
                Profile(onClearBackStack = onClearBackStack)
            }
        }
    )
}

/**
 * Esta preview como utiliza navegación debe usarse
 * el emulador o un dispositivo real para ver el resultado
 */
@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    PastelTheme {
        Scaffold { innerPadding ->
            MainScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}