package es.rafapuig.pmdm.compose.exercises.navigation.exercise2.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.FootballTeam
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.FootballTeams


@Composable
fun FootballTeamListItem(
    team: FootballTeam,
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier.clickable { onItemSelected(team.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FootballTeamBadge(team = team, Modifier.size(100.dp))
            Text(text = team.name, style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FootballTeamListItemPreview() {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FootballTeams.forEach { team ->
                FootballTeamListItem(team)
            }
        }
    }
}
