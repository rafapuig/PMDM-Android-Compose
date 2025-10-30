package es.rafapuig.pmdm.compose.exercises.navigation.exercise2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.screens.FootballTeamDetailsScreen
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.screens.FootballTeamsListScreen
import kotlinx.serialization.Serializable

@Serializable
object FootballTeamsListScreenKey : NavKey

@Serializable
data class FootballTeamDetailsScreenKey(val teamId: Int) : NavKey

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    // Create la back stack con la pantalla inicial
    val backStack = rememberNavBackStack(FootballTeamsListScreenKey)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<FootballTeamsListScreenKey> { screen ->
                FootballTeamsListScreen { teamId ->
                    backStack.add(FootballTeamDetailsScreenKey(teamId))
                }
            }
            entry<FootballTeamDetailsScreenKey> { screen ->
                FootballTeamDetailsScreen(screen.teamId) {
                    backStack.removeLastOrNull()
                }
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