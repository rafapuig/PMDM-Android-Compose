package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.search.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.presentation.screens.MoviesListScreen
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.search.presentation.SearchMoviesViewModel
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.search.presentation.components.SearchField2
import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.ui.theme.PMDMComposeTheme

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
            topBar = { // Usamos un Box como contenedor para aplicar los paddings del sistema
                Box(
                    // 1. APLICA EL PADDING DE LA BARRA DE ESTADO
                    modifier = Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.statusBars),
                    contentAlignment = Alignment.Center
                ) {
                    // 2. Coloca tu SearchField dentro de este Box
                    //    con su propio padding para separarlo de los bordes
                    SearchField2(
                        query = searchQuery,
                        onQueryChange = viewModel::onSearchQueryChanged,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        )
    }
}