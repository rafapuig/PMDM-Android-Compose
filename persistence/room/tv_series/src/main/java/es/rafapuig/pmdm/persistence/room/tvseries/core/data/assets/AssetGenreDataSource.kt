package es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets

import android.content.Context
import es.rafapuig.pmdm.persistence.room.tvseries.core.data.assets.dto.GenreListDto
import es.rafapuig.pmdm.persistence.room.tvseries.core.domain.model.Genre
import kotlinx.serialization.json.Json

class AssetGenreDataSource(
    private val context: Context
) {
    private val json = Json { ignoreUnknownKeys = true }

    fun loadGenres(): List<Genre> {
        val jsonString = localizedAsset(context, "Genres.json")
        val genresDto = json.decodeFromString<GenreListDto>(jsonString)
        return genresDto.genres.map { genreDto ->
            genreDto.toDomain()
        }
    }
}


