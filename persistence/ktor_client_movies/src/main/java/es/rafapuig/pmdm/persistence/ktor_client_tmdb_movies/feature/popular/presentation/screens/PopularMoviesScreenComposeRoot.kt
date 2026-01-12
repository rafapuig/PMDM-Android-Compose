package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.popular.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.presentation.screens.MoviesListScreen
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.TMDBApiServiceImpl
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun PopularMoviesScreenComposeRoot() {

    val apiService = remember { TMDBApiServiceImpl.create() }

    val popularMovies by produceState(emptyList()) {
        value = apiService.fetchPopularMovies().results.map {
            it.toDomain()
        }
    }

    PMDMComposeTheme {
        MoviesListScreen(popularMovies)
    }
}