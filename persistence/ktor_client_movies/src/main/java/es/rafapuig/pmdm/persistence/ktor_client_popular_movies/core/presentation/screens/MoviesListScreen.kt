@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.components.MoviesGrid
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.components.ScrollToTopFAB
import kotlinx.coroutines.launch

@Composable
fun MoviesListScreen(
    movies: List<Movie>,
    isLoading: Boolean = false,
    title: String = "Peliculas",
    onLoadMoreMovies: () -> Unit = {},
    topPlaceholder: @Composable () -> Unit = {},
) {
    val gridState = rememberLazyGridState()

    val showBackToTopButton by remember {
        derivedStateOf {
            gridState.firstVisibleItemIndex > 4
        }
    }

    val scope = rememberCoroutineScope()

    fun animateScrollToTop() = scope.launch {
        gridState.animateScrollToItem(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (showBackToTopButton) {
                ScrollToTopFAB(onClick = { animateScrollToTop() })
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            topPlaceholder()
            MoviesGrid(
                movies = movies,
                isLoading = isLoading,
                onLoadMoreMovies = onLoadMoreMovies,
                gridState = gridState,
                //modifier = Modifier.padding(innerPadding)
            )
        }
    }
}