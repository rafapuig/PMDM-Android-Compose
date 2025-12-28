package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.search.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.screens.MoviesListScreen
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.search.presentation.SearchMoviesViewModel
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.search.presentation.components.SearchField
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.top_rated.presentation.TopRatedMoviesViewModel
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.ui.theme.PMDMComposeTheme

@Preview
@Composable
fun SearchMoviesScreenRoot(
    viewModel: SearchMoviesViewModel = viewModel(
        factory = SearchMoviesViewModel.Factory
    )
) {
    val movies by viewModel.movies
        .collectAsStateWithLifecycle()

    val isLoading by viewModel.isLoading
        .collectAsStateWithLifecycle()


    val searchQuery by viewModel.searchQuery
        .collectAsStateWithLifecycle()

    PMDMComposeTheme {
        MoviesListScreen(
            title = "Peliculas",
            movies = movies,
            isLoading = isLoading,
            onLoadMoreMovies = viewModel::onLoadMoreMovies,
            topPlaceholder = {
                SearchField(
                    searchQuery,
                    viewModel::onSearchQueryChanged
                )
            }
        )
    }
}