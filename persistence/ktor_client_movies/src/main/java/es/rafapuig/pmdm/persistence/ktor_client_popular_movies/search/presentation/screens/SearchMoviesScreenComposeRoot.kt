package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.search.presentation.screens

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
fun SearchMoviesScreenComposeRoot() {

    val apiService = remember { TMDBApiServiceImpl.create() }

    val movies by produceState(emptyList()) {
        value = apiService.searchMovies("titanic").results.map {
            it.toDomain()
        }
    }

    PMDMComposeTheme {
        MoviesListScreen(movies)
    }
}