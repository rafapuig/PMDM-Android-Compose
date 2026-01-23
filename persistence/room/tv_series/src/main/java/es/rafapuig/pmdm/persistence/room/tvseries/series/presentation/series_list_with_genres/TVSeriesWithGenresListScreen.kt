package es.rafapuig.pmdm.persistence.room.tvseries.series.presentation.series_list_with_genres

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.TVSeries
import es.rafapuig.pmdm.persistence.room.tvseries.core.presentation.components.Poster
import es.rafapuig.pmdm.persistence.room.tvseries.core.presentation.components.PosterSize

@Composable
fun TVSeriesWithGenresListScreen(
    tvSeriesList: List<TVSeries>
) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tvSeriesList) {
                Column() {
                    Poster(
                        posterPath = it.posterPath,
                        size  = PosterSize.W500,
                        //modifier = Modifier.height(100.dp)
                    )
                    Column() {
                        Text(it.name, style = MaterialTheme.typography.titleLarge)
                        FlowRow {
                            it.genres.forEach { genre ->
                                SuggestionChip(
                                    onClick = {},
                                    label = { Text(genre.name) }
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}