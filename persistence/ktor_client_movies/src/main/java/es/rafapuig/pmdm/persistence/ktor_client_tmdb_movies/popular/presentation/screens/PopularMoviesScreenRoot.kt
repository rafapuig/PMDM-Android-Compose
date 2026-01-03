package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.popular.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.presentation.screens.MoviesListScreen
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.popular.presentation.PopularMoviesViewModel
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun PopularMoviesScreenRoot(
    viewModel: PopularMoviesViewModel = viewModel(
        factory = PopularMoviesViewModel.Factory
    )
) {
    val movies by viewModel.movies
        .collectAsStateWithLifecycle()

    val isLoading by viewModel.isLoading
        .collectAsStateWithLifecycle()

    PMDMComposeTheme {
        MoviesListScreen(
            title = "Popular",
            movies = movies,
            isLoading = isLoading,
            onLoadMoreMovies = viewModel::onLoadMoreMovies
        )
    }
}