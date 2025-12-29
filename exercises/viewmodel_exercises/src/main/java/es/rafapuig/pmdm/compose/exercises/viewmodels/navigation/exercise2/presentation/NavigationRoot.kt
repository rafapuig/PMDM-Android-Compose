package es.rafapuig.pmdm.compose.exercises.viewmodels.navigation.exercise2.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.compose.exercises.viewmodels.navigation.exercise2.MenuDestination
import es.rafapuig.pmdm.compose.exercises.viewmodels.navigation.exercise2.counter.presentation.screens.CounterScreenRoot
import es.rafapuig.pmdm.compose.exercises.viewmodels.navigation.exercise2.presentation.screens.MenuScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeScreenKey : NavKey

@Serializable
object CounterScreenKey : NavKey

@Composable
fun NavigationRoot() {

    val navBackStack = rememberNavBackStack(HomeScreenKey)

    NavDisplay(
        backStack = navBackStack,
        onBack = { if (navBackStack.size > 1) navBackStack.removeLastOrNull() },

        entryDecorators = listOf(
            // Add the default decorators for managing scenes and saving state
            rememberSaveableStateHolderNavEntryDecorator(),
            // Then add the view model store decorator
            /**
             * Para dar alcance del viemodel limitado a una entrada de la NavBackStack
             * en lugar de la actividad
             */
            rememberViewModelStoreNavEntryDecorator()
        ),

        entryProvider = entryProvider {

            entry<HomeScreenKey> { key ->
                MenuScreen(
                    onNavigate = { destination ->
                        when (destination) {
                            MenuDestination.Sensors -> TODO()
                            MenuDestination.Settings -> TODO()
                            MenuDestination.Dice -> TODO()
                            MenuDestination.Converter -> TODO()
                            MenuDestination.Counter -> navBackStack.add(CounterScreenKey)
                        }
                    }
                )
            }

            entry<CounterScreenKey> { key ->
                CounterScreenRoot(onBack = { navBackStack.removeLastOrNull() })
            }

        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun NavigationRootPreview() {
    NavigationRoot()
}