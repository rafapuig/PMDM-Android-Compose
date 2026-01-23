package es.rafapuig.pmdm.persistence.room.tvseries.genres.presentation.genres_list

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun GenresListRoot(
    viewModel: GenreListViewModel = koinViewModel()
) {
    val genres = viewModel.genres
        .collectAsStateWithLifecycle()

    GenresList(genres = genres.value)
}