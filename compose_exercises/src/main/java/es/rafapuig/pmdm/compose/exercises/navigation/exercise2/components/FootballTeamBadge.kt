package es.rafapuig.pmdm.compose.exercises.navigation.exercise2.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import es.rafapuig.pmdm.compose.exercises.R
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.FootballTeam
import es.rafapuig.pmdm.compose.exercises.navigation.exercise2.FootballTeams


@Composable
fun FootballTeamBadge(team: FootballTeam, modifier: Modifier = Modifier) {

    val model = ImageRequest.Builder(LocalContext.current)
        .data(team.badgeURL)
        .httpHeaders(
            NetworkHeaders.Builder()
                .set("User-Agent", "Mozilla/5.0")
                .build()
        ).build()

    AsyncImage(
        model = model,
        contentDescription = team.name,
        contentScale = ContentScale.Companion.FillHeight,
        modifier = modifier,
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
    )
}

@Preview(showBackground = true)
@Composable
fun FootballTeamBadgePreview() {
    FootballTeamBadge(FootballTeams[0], modifier = Modifier.size(100.dp))
}