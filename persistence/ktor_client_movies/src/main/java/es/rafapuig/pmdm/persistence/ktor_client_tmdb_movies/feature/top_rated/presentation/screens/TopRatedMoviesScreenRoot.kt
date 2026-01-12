package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.top_rated.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.presentation.screens.MoviesListScreen
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.feature.top_rated.presentation.TopRatedMoviesViewModel
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun TopRatedMoviesScreenRoot(
    viewModel: TopRatedMoviesViewModel = viewModel(
        factory = TopRatedMoviesViewModel.Factory
    )
) {
    val movies by viewModel.movies
        .collectAsStateWithLifecycle()

    val isLoading by viewModel.isLoading
        .collectAsStateWithLifecycle()


    PMDMComposeTheme {
        MoviesListScreen(
            title = "Mejor puntuadas",
            movies = movies,
            isLoading = isLoading,
            onLoadMoreMovies = viewModel::onLoadMoreMovies
        )
    }
}