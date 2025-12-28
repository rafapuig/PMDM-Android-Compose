@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.screens

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
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.components.MoviesGrid
import kotlinx.coroutines.launch

@Composable
fun MoviesListScreen(
    movies: List<Movie>,
    isLoading: Boolean = false,
    title: String = "Peliculas",
    onLoadMoreMovies: () -> Unit = {}
) {
    val gridState = rememberLazyGridState()

    val showBackToTopButton by remember {
        derivedStateOf {
            gridState.firstVisibleItemIndex > 4
        }
    }

    val scope = rememberCoroutineScope()

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
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            gridState.animateScrollToItem(0)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,//.KeyboardArrowUp,
                        contentDescription = "Volver al inicio"
                    )
                }
            }

        }
    ) { innerPadding ->
        MoviesGrid(
            movies = movies,
            isLoading = isLoading,
            onLoadMoreMovies = onLoadMoreMovies,
            gridState = gridState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}