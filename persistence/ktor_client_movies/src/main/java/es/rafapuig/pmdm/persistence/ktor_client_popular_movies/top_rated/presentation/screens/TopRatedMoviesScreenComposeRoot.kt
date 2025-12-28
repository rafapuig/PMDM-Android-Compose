package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.top_rated.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.TMDBApiServiceImpl
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.data.remote.toDomain
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.screens.MoviesListScreen
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun TopRatedMoviesScreenComposeRoot() {

    val apiService = remember { TMDBApiServiceImpl.create() }

    val popularMovies by produceState(emptyList()) {
        value = apiService.getTopRatedMovies().results.map {
            it.toDomain()
        }
    }

    PMDMComposeTheme {
        MoviesListScreen(popularMovies)
    }
}