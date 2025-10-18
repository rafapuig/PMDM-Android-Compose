package es.rafapuig.pmdm.compose.learning.lists

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.learning.R

val CardContainerColor = Color(0xFF1F33A2)

@Preview(showBackground = false)
@Composable
fun FootballTeamItem(
    team: FootballTeam = FootballTeam(
        stringArrayResource(R.array.football_teams_names)[4],
        R.drawable.levante_ud_logo
    ),
    onItemClick: (FootballTeam) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = CardContainerColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        onClick = { onItemClick(team) }

    ) {
        Row(
            modifier = Modifier.Companion
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Companion.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = team.badge),
                contentDescription = null,
                modifier = Modifier.Companion
                    .weight(.15f)
                    .height(70.dp),
                contentScale = ContentScale.Companion.Fit
            )
            Spacer(modifier = Modifier.Companion.weight(.05f))
            Text(
                modifier = Modifier.Companion
                    .weight(.8f)
                    .padding(16.dp),
                text = team.name,
                fontSize = 32.sp,
                color = Color.Companion.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FootballTeamItemPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FootballTeamItem()
        FootballTeamItem()
    }
}
