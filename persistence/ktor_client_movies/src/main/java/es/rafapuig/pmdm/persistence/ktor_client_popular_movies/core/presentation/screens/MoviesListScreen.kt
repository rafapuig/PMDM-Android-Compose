@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.domain.model.Movie
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.components.MoviesGrid
import es.rafapuig.pmdm.persistence.ktor_client_popular_movies.core.presentation.components.ScrollToTopFAB
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@Composable
fun MoviesListScreen(
    movies: List<Movie>,
    isLoading: Boolean = false,
    title: String = "Peliculas",
    onLoadMoreMovies: () -> Unit = {},
    topBar: (@Composable () -> Unit)? = null,
) {

    /** https://developer.android.com/develop/ui/compose/lists#control-scroll-position */

    val gridState = rememberLazyGridState()

    val showBackToTopButton by remember {
        derivedStateOf {
            gridState.firstVisibleItemIndex > 0
        }
    }

    val scope = rememberCoroutineScope()

    fun animateScrollToTop() = scope.launch {
        gridState.animateScrollToItem(0)
    }

    Scaffold(
        topBar = {
            topBar?.invoke() ?: TopAppBar(
                title = {
                    Text(text = title)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AnimatedVisibility(
                visible = showBackToTopButton,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ScrollToTopFAB(onClick = { animateScrollToTop() })
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            //topPlaceholder()
            MoviesGrid(
                movies = movies,
                isLoading = isLoading,
                gridState = gridState,
                //modifier = Modifier.padding(innerPadding)
            )
        }
    }

    /** https://developer.android.com/develop/ui/compose/lists#react-to-scroll-position */

    // Efecto que detecta el final del scroll
    LaunchedEffect(gridState) {

        // Definimos un umbral, por ejemplo, 3.
        // (numero impar pq cuando se esta cargando se añade
        // momentaneamente el item de indicador progreso)
        // Cuando el usuario vea el 3º último elemento,
        // tenemos que cargar más.
        val threshold = 3

        // 'snapshotFlow' convierte el estado del scroll en un Flow de Kotlin
        snapshotFlow { gridState.layoutInfo }
            // A partir de la info del layout, determinamos si hay que hacer una carga o no
            .map { layoutInfo ->
                // Obtenemos el índice del último elemento visible
                // Si no hay elementos (por ejemplo, al principio), retornamos false
                val lastVisibleItemIndex =
                    layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return@map false

                val totalItems = layoutInfo.totalItemsCount
                val lastItemIndex = totalItems - 1

                lastItemIndex - lastVisibleItemIndex <= threshold
            }
            .distinctUntilChanged() // Mientras no cambie el valor emitido, filtrar
            .filter { shouldLoadMore -> shouldLoadMore } // Solo valores true
            .onEach { shouldLoadMore ->
                println("Cargando más...")
                // ¡Llama a la función para cargar más!
                if(shouldLoadMore) onLoadMoreMovies()
            }
            .launchIn(this)

    }
}