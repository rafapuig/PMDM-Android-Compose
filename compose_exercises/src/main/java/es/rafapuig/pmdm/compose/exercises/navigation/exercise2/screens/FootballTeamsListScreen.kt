package es.rafapuig.pmdm.compose.exercises.navigation.exercise2.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.FootballTeams
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.components.FootballTeamListItem

@Preview
@Composable
fun FootballTeamsListScreen(
    modifier: Modifier = Modifier,
    onTeamSelected: (teamId: Int) -> Unit = {}) {

    LazyColumn(
        modifier = modifier
    ) {
        items(FootballTeams) {
            FootballTeamListItem(
                team = it,
                onItemSelected = onTeamSelected
            )
        }
    }
}