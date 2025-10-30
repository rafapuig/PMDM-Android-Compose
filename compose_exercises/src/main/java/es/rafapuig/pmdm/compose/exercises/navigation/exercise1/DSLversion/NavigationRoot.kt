package es.rafapuig.pmdm.compose.exercises.navigation.exercise1.DSLversion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.screens.HomeScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.screens.ProfileScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.screens.SettingsScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.HomeScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.ProfileScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise1.SettingsScreen

@Preview
@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(HomeScreen)

    val onNavigateTo = { screen: NavKey -> backStack.add(screen) }
    val onNavigateBack = { backStack.removeLastOrNull() }


    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<HomeScreen> {
                HomeScreen(
                    onNavigateToSettings = { onNavigateTo(SettingsScreen) },
                    onNavigateToProfile = { onNavigateTo(ProfileScreen(it)) }
                )
            }
            entry<ProfileScreen> {
                ProfileScreen(
                    userId = it.userId,
                    onBack = backStack::removeLastOrNull
                )
            }
            entry<SettingsScreen> {
                SettingsScreen(
                    onBack = { onNavigateBack() }
                )
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun NavigationRootPreview() {
    Scaffold { innerPadding ->
        NavigationRoot(modifier = Modifier.padding(innerPadding))
    }
}