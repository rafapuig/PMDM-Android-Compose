package es.rafapuig.pmdm.compose.exercises.navigation.exercise1.directly

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.HomeScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.ProfileScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.SettingsScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.screens.HomeScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.screens.ProfileScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.screens.SettingsScreen

/**
 * https://developer.android.com/guide/navigation/navigation-3/save-state
 */

@Composable
fun NavigationRootEntryProviderDirectly(modifier: Modifier = Modifier) {
    /**
     * la función rememberNavBackStack esta diseñada para crear un backStackque persiste
     * a los cambios de configuracion (giro del dispostivo, modo oscuro, etc)
     * y a la muerte del proceso (si el SO se queda sin memoria y lo elimina)
     */
    val backStack = rememberNavBackStack(HomeScreen)

    /**
     * Para volver a la pantalla anterior hay que eliminar el último elemento de la pila
     */
    val onNavigationBack: () -> NavKey? =
        backStack::removeLastOrNull // { backStack.removeLastOrNull() }

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
        /**
         * Creamos la funcion entryProvider que recibe un NavKey y devuelve un NavEntry
         */
        entryProvider = { navKey -> //Clave de navegación para la que vamos a generar el NavEntry
            /**
             * Lambda para generar cada posible NavEntry (por cada NavKey)
             */
            when (navKey) {
                /**
                 * Al último parametro del constructor de NavEntry le estamos pasando una lambda
                 * como argumento, la lambda es una función que recibe el NavKey como parámetro
                 */
                is HomeScreen -> NavEntry(navKey) {
                    HomeScreen(
                        /**
                         * Para navegar a otra pantalla hay que ponerla en la cima de la pila
                         */
                        onNavigateToProfile = { userId -> backStack.add(es.rafapuig.pmdm.compose.exercises.navigation.exercise1.ProfileScreen(userId)) },
                        onNavigateToSettings = { backStack.add(SettingsScreen) }
                    )
                }

                is ProfileScreen -> NavEntry(navKey) {
                    ProfileScreen(
                        userId = navKey.userId, // Smart cast de NavKey a Profile (por eso accede a userId)
                        onBack = { onNavigationBack() } // backStack::removeLastOrNull
                    )
                }

                is SettingsScreen -> NavEntry(key = navKey) {
                    SettingsScreen(
                        onBack = { backStack.removeLastOrNull() }
                    )
                }

                else -> throw IllegalArgumentException("Invalid navKey: $navKey")
            }
        }
    )
}


@Preview(showSystemUi = true)
@Composable
fun NavigationRootPreview() {
    Scaffold { innerPadding ->
        NavigationRootEntryProviderDirectly(modifier = Modifier.padding(innerPadding))
    }
}