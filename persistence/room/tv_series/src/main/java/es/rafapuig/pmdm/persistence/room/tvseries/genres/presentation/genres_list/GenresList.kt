package es.rafapuig.pmdm.persistence.room.tvseries.genres.presentation.genres_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.AssetGenreDataSource
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.Genre
import es.rafapuig.pmdm.persistence.room.tvseries.ui.theme.PMDMComposeTheme

@Composable
fun GenresList(
    genres: List<Genre>
) {
    Scaffold() {innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            items(genres) {
                Text(text = it.name)
            }
        }
    }
}

@Preview
@Composable
fun GenresListPreview() {
    val dataSource = AssetGenreDataSource(LocalContext.current)

    val genres = dataSource.loadGenres()
    PMDMComposeTheme(true) {
        GenresList(genres = genres)
    }
}