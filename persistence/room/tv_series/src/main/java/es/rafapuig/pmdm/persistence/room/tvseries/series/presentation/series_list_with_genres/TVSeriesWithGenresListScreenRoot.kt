package es.rafapuig.pmdm.persistence.room.tvseries.series.presentation.series_list_with_genres

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun TVSeriesWithGenresListScreenRoot(
    viewModel: TVSeriesWithGenresViewModel = koinViewModel()
) {
    val tvSeriesWithGenres = viewModel.tvSeriesWithGenres
        .collectAsStateWithLifecycle()

    TVSeriesWithGenresListScreen(
        tvSeriesList = tvSeriesWithGenres.value
    )
}