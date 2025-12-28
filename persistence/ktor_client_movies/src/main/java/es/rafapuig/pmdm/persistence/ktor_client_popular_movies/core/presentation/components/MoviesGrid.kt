package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.ui.theme.PMDMComposeTheme

@Composable
fun MoviesGrid(
    movies: List<Movie> = emptyList(),
    isLoading: Boolean = false,
    onLoadMoreMovies: () -> Unit = {},
    gridState: LazyGridState = rememberLazyGridState(),
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        state = gridState,
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(
            items = movies,
            //key = { movie -> movie.id }
        ) { movie ->
            MovieCard(
                movie = movie,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Añade un ítem al final para mostrar el indicador de carga
        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    // Efecto que detecta el final del scroll
    LaunchedEffect(gridState) {
        // 'snapshotFlow' convierte el estado del scroll en un Flow de Kotlin
        snapshotFlow { gridState.layoutInfo }
            .collect { layoutInfo ->
                // Obtenemos el índice del último elemento visible
                val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                // Definimos un umbral, por ejemplo, 5.
                // Cuando el usuario vea el 5º último elemento, empezamos a cargar más.
                val threshold = 5
                val totalItems = layoutInfo.totalItemsCount

                // Si no estamos ya cargando y el usuario ha alcanzado el umbral...
                if (isLoading && lastVisibleItemIndex >= totalItems - 1 - threshold) {
                    // ¡Llama a la función del ViewModel para cargar más!
                    onLoadMoreMovies()
                }
            }

    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MoviesGridPreview() {
    PMDMComposeTheme {
        Surface {
            MoviesGrid()
        }
    }
}