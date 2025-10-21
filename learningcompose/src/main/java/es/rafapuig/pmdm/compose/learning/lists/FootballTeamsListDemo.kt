package es.rafapuig.pmdm.compose.learning.lists

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.learning.R

data class FootballTeam(
    val name: String,
    @DrawableRes val badge: Int
)

/**
 * Al hacer un remember de un objeto "normal" (no un State)
 * Compose recuerda el objeto entre recomposiciones
 * (Pero si cambia el estado del objeto no se genera ninguna recomposición)
 *
 * Si cambiara la lista de equipos de futbol no se recompondría la UI
 */
@Composable
fun rememberFootballTeams(): List<FootballTeam> {

    val resources = LocalResources.current

    return remember(resources.configuration) {
        val names = resources.getStringArray( R.array.football_teams_names).toList()
        val badgesArray = resources.obtainTypedArray(R.array.football_teams_badges)

        val badges = List(badgesArray.length()) { index ->
            badgesArray.getResourceId(index, 0)
        }
        badgesArray.recycle()

        val teams = names.zip(badges).map { (name, badge) ->
            FootballTeam(name, badge)
        }
        teams
    }
}



@Composable
fun FootballTeamList(
    teams: List<FootballTeam>,
    modifier: Modifier = Modifier,
    onItemClick: (FootballTeam) -> Unit = {}
) {

    val scrollState = rememberScrollState()

    Column(modifier = modifier.verticalScroll(scrollState)) {
        repeat(teams.size) { index ->
            FootballTeamItem(team = teams[index], onItemClick = onItemClick)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun FootballTeamsScreen() {
    val context = LocalContext.current

    val onItemClick = { team: FootballTeam ->
        Toast
            .makeText(context, team.name, Toast.LENGTH_SHORT)
            .show()
    }

    /**
     * Al hacer un remember de un objeto "normal" (no un State)
     * Compose recuerda el objeto entre recomposiciones
     * Los equipos de futbol no se vuelven a cargar entre recomposiciones
     */
    val teams = rememberFootballTeams()

    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            FootballTeamList(teams = teams, onItemClick = onItemClick)
        }
    }
}
