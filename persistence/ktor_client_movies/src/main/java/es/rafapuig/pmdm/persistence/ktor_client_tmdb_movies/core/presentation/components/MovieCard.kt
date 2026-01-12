package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    val widthPx = 500
    val density = LocalDensity.current
    val width = remember { with(density) { widthPx.toDp() } }

    OutlinedCard(
        modifier = modifier.width(width),
        //border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        )

    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            MoviePoster(
                posterPath = movie.posterPath,
                contentDescription = movie.title,
                size = PosterSize.W500,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CardDefaults.outlinedShape)
            )

            RatingCircle(
                size = 52.dp,
                rating = movie.voteAverage,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = 6.dp, y = (12).dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(100.dp)
                .padding(4.dp)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieCardPreview() {
    PMDMComposeTheme {
        Surface {
            MovieCard(
                movie = Movie(
                    id = 1,
                    title = "Ratatouille",
                    overview = "Una rata cocinera",
                    posterPath = "/nGUelOVetiRpY2wTBMHTbrTIGYC.jpg",
                    voteAverage = 7.2
                )
            )
        }
    }
}