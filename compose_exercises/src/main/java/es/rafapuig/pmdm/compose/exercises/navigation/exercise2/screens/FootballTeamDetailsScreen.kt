package es.rafapuig.pmdm.compose.exercises.navigation.exercise2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.FootballTeams
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.components.FootballTeamBadge

@Preview(showSystemUi = true)
@Composable
fun FootballTeamDetailsScreen(
    teamId: Int = 1,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    val team = FootballTeams.first { it.id == teamId }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FootballTeamBadge(team, modifier = Modifier.height(300.dp))
        Text(team.name, style = MaterialTheme.typography.displayLarge)
        Text(team.city ?: "", style = MaterialTheme.typography.headlineLarge)
        Text(team.stadium ?: "", style = MaterialTheme.typography.headlineSmall)
        Button(onBack) {
            Text("Volver")
        }

    }



}