package es.rafapuig.pmdm.compose.learning.lists.lazy

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.R

data class FootballTeam(
    val name: String,
    @DrawableRes val badge: Int
)

@SuppressLint("LocalContextResourcesRead", "Recycle")
@Composable
fun rememberFootballTeams(): List<FootballTeam> {

    val context = LocalContext.current

    return remember {
        val names = context.resources.getStringArray( R.array.football_teams_names).toList()
        val badgesArray = context.resources.obtainTypedArray(R.array.football_teams_badges)

        val badges = List(badgesArray.length()) { index ->
            badgesArray.getResourceId(index, 0)
        }

        val teams = names.zip(badges).map { (name, badge) ->
            FootballTeam(name, badge)
        }
        teams
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

    val teams = rememberFootballTeams()

    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            FootballTeamList(teams = teams, onItemClick = onItemClick)
        }
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


val CardContainerColor = Color(0xFF1F33A2)


@Preview(showBackground = true)
@Composable
fun FootballTeamItem(
    team: FootballTeam = FootballTeam(
        "Real Madrid C.F.",
        R.drawable.real_madrid
    ),
    onItemClick: (FootballTeam) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = CardContainerColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .clickable { onItemClick(team) }

    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = team.badge),
                contentDescription = null,
                modifier = Modifier.weight(.15f).height(70.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.weight(.05f))
            Text(
                modifier = Modifier
                    .weight(.8f)
                    .padding(16.dp),
                text = team.name,
                fontSize = 32.sp,
                color = Color.White
            )
        }
    }
}

